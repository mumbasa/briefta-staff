package com.briefta.staff.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briefta.staff.model.ReceptionistMessage;
import com.briefta.staff.model.ResponseMessage;
import com.briefta.staff.model.StaffMessage;
import com.briefta.staff.repository.ReceptionistMessageRepository;
import com.briefta.staff.repository.StaffMessageRepository;

@Service
public class ReceptionMessageService {

	@Autowired
	ReceptionistMessageRepository receptionistMessageRepository;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	StaffMessageRepository staffMessageRepository;
	public ResponseMessage saveMessage(ReceptionistMessage message) {
		message.setDate(LocalDateTime.now());
		ResponseMessage response = new ResponseMessage();
		response.setStatusCode(200);
		response.setMessage("Message sent to Receptionist");
		response.setPayload(receptionistMessageRepository.save(message));
		messageService.sendNotifyStaff("reception:"+message.getCompanyId(),"You have a new message");
		return response;
		
	}
	
	public ResponseMessage deleteMessages(long messageId) {
		ResponseMessage response = new ResponseMessage();
		response.setStatusCode(200);
		response.setMessage("Message Deleted");
		receptionistMessageRepository.deleteById(messageId);
		return response;
		
	}
	
	public ResponseMessage GetStaffMessages(long staffId) {
		List<StaffMessage> msg = staffMessageRepository.findByStaffId(staffId);
		ResponseMessage response = new ResponseMessage();
		response.setStatusCode(200);
		response.setMessage("Message fetched: "+msg.size());
		response.setPayload(msg);
		return response;
		
	}
	 
}
