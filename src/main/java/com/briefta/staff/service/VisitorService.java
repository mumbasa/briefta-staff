package com.briefta.staff.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.briefta.staff.model.Visitor;
import com.briefta.staff.model.ResponseMessage;
import com.briefta.staff.model.Staff;
import com.briefta.staff.model.Visit;
import com.briefta.staff.repository.StaffRepository;
import com.briefta.staff.repository.VisitRepository;
import com.briefta.staff.repository.VisitTagRepository;
import com.briefta.staff.repository.VisitorRepository;



@Service
public class VisitorService {
	@Autowired
	VisitorRepository visitorRepository;
	@Autowired
	VisitRepository visitRepository;
	@Autowired
	VisitTagRepository visitTagRepository;
	
	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
	MessageService messageService;

	@Value("${file.upload-dir}")
	String UPLOAD_FOLDER;

	@Value("${spring.application.name}")
	String appName;

	
	
	public ResponseMessage getStaffVisitors(long staffId,int page,int size) {
		Pageable pageable = PageRequest.of(page, size);
		List<Visit> visits = visitRepository.findVisitsByStaffId(staffId, pageable);
		ResponseMessage message = new ResponseMessage();
		message.setMessage("Records retrieved :"+visits.size());
		message.setStatusCode(200);
		message.setPayload(visits);
		return message;
	}


	public ResponseMessage getStaffVisitorsByStatus(long staffId,String status) {
		List<Visit> visits = visitRepository.findDayVisitsByStatus(staffId, status);
		ResponseMessage message = new ResponseMessage();
		message.setMessage("Records retrieved :"+visits.size());
		message.setStatusCode(200);
		message.setPayload(visits);
		return message;
	}

	public ResponseMessage replyVisit(Visit visitIn) {
		Visit visit = visitRepository.findById(visitIn.getId()).get();
		visit.setVisitStatus(visitIn.getVisitStatus());
		ResponseMessage message = new ResponseMessage();
		message.setMessage("Visitor Alerted");
		message.setStatusCode(200);
		message.setPayload(visitRepository.save(visit));
		String responseMessage = visit.getStaff().getFirstName() +" "+visit.getStaff().getLastName()+" has "+
		visitIn.getVisitStatus()+" visit from "+visit.getVisitor().getName();
		messageService.sendNotifyStaff("reception:"+visit.getCompanyId(),responseMessage);
		return message;
	}
	

	public Visitor getVisitor(long staffId) {
		Staff staff = staffRepository.findById(staffId).get();
		Optional<Visitor> getVistor = visitorRepository.findVisitorByTelephone(staff.getContactInfo());

		if (getVistor.isEmpty()) {
			Visitor v = new Visitor();
			v.setCompany(staff.getDepartment().getCompany().getCompanyName());
			v.setDataAdded(LocalDateTime.now());
			v.setName(staff.getFirstName()+" "+(staff.getMiddleName().length()>0?staff.getMiddleName()+" " :" "+staff.getLastName()));
			v.setDataAdded(LocalDateTime.now());
			v.setTelephone(staff.getContactInfo());
			return (visitorRepository.saveAndFlush(v));
		} else {
			return getVistor.get();
		}

	}

	@Transactional
	public ResponseMessage saveVisit(Visit visit) {
		
		Visitor v = getVisitor(visit.getVisitor().getStaffid());
		Staff staff = staffRepository.findById(visit.getStaffId()).get();

		ResponseMessage message = new ResponseMessage();
		visit.setVisitorId(v.getId());
		visit.setVisitor(v);
		visit.setStaff(staff);
		visit.setTimeIn(LocalDateTime.now());
		visit.setPicture(decodeBase64String(visit.getPicture()));
		visit.setVisitStatus("Pending");
		visitRepository.saveAndFlush(visit);
		System.err.println(visit.getStaff().getContactInfo()+"---");
		messageService.sendNotifyStaff(visit);
		message.setMessage("Staff Alerted of Visit");
		message.setStatusCode(200);
		message.setPayload(visit);
		return message;
	}
	
	public String decodeBase64String(String fileString) {
		String filename = System.currentTimeMillis() + ".jpg";
		byte dearr[] = Base64.getDecoder().decode(fileString.replaceAll(" ", "+"));
		try {
			FileUtils.writeByteArrayToFile(new File(UPLOAD_FOLDER + appName + "/" + filename), dearr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filename;

	}
}
