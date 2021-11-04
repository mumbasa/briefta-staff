package com.briefta.staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briefta.staff.model.ResponseMessage;
import com.briefta.staff.model.Visit;
import com.briefta.staff.service.VisitorService;

@RestController
public class VisitorController {

	@Autowired
	VisitorService visitorService;

	@GetMapping("/my/visits")
	public ResponseMessage getMyVisits(@RequestParam long staffId, @RequestParam int page, @RequestParam int size) {
		return visitorService.getStaffVisitors(staffId, page, size);
	}

	@GetMapping("/visits/by/status")
	public ResponseMessage getMyVisitsByStatus(@RequestParam long staffId, @RequestParam String status) {
		return visitorService.getStaffVisitorsByStatus(staffId, status);

	}

	@PostMapping("/visitor/visit/service")
	public ResponseMessage staffToVisit(@RequestBody Visit visit) {
		return visitorService.saveVisit(visit);

	}
	
	@PostMapping("/visit/respond")
	public ResponseMessage respondToVisit(@RequestBody Visit visit) {
		return visitorService.replyVisit(visit);

	}

}
