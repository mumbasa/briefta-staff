package com.briefta.staff.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.briefta.staff.model.ResponseMessage;
import com.briefta.staff.model.Staff;
import com.briefta.staff.repository.StaffRepository;

@Service
public class StaffService {
	@Autowired
	StaffRepository staffRepository;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	MessageService messageService;

	public ResponseMessage getCompanyStaff(long companyId,int page,int size) {
		Pageable pageable =PageRequest.of(page, size); 
		List<Staff> staff = staffRepository.findStaffInCompany(companyId, pageable);
		ResponseMessage message = new ResponseMessage();
		message.setStatusCode(200);
		message.setPayload(staff);
		message.setMessage(staff.size() +" record size found");
		return message;
	
	
	}
	
	public ResponseMessage changePassword(String contactInfo) {
		ResponseMessage message = new ResponseMessage();
		Optional<Staff> staff = staffRepository.findStaffBycontactInfo(contactInfo);
		if(staff.isPresent()) {
		String generatedString = RandomStringUtils.random(6, false, true);
		staff.get().setPassword(passwordEncoder.encode(generatedString));
		message.setStatusCode(200);
		message.setPayload(staffRepository.save(staff.get()));
		message.setMessage("Password will be sent you shortly");
		messageService.sendSms("Your new pin to user Briefta-VMS is  "+generatedString, contactInfo);
		}else {
			message.setStatusCode(200);
			message.setMessage("User does not exist");

			
		}
		return message;
	
	
	}
		
	public ResponseMessage getCompanyStaff(String name, long companyId,int page,int size) {
		Pageable pageable =PageRequest.of(page, size); 
		List<Staff> staff = staffRepository.searchStaff(name,companyId, pageable);
		ResponseMessage message = new ResponseMessage();
		message.setStatusCode(200);
		message.setPayload(staff);
		message.setMessage(staff.size() +" record size found");
		return message;
	
	
	}
		
	

}
