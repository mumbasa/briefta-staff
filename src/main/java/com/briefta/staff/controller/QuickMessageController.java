package com.briefta.staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briefta.staff.model.ReceptionistMessage;
import com.briefta.staff.model.ResponseMessage;
import com.briefta.staff.service.ReceptionMessageService;
import com.briefta.staff.repository.QuickMessageRepository;

@RestController
@RequestMapping("/v1/api")
public class QuickMessageController {

	@Autowired
	QuickMessageRepository quickMessageRepository;

	@Autowired
	ReceptionMessageService receptionMessageService;

	@PostMapping("/message")
	public ResponseMessage sendReceptionistMessage(@RequestBody ReceptionistMessage message) {
		return receptionMessageService.saveMessage(message);
	}

	@GetMapping("/messages")
	public ResponseMessage getMessages(@RequestParam long staffId) {
		return receptionMessageService.GetStaffMessages(staffId);

	}

	@DeleteMapping("/message")
	public ResponseMessage getMessage(@RequestParam long messageId) {
		return receptionMessageService.deleteMessages(messageId);
	}

	

}
