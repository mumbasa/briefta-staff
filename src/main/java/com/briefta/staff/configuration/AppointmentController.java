package com.briefta.staff.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briefta.staff.model.Appointment;
import com.briefta.staff.model.ResponseMessage;
import com.briefta.staff.service.AppointmentService;

@RestController
@RequestMapping("/v1/api")
public class AppointmentController {
	
	@Autowired
	AppointmentService appointmentService;
	
	@GetMapping("/appointments/by/status")
	public ResponseMessage getAppointments(@RequestParam long staffId,@RequestParam int page,@RequestParam int size,@RequestParam String status) {
		return appointmentService.getAppointment(staffId, page, size, status);
		
		
	}
	
	@GetMapping("/appointments/upcoming")
	public ResponseMessage getUpcomingAppointments(@RequestParam long staffId,@RequestParam int page,@RequestParam int size) {
		return appointmentService.getAppointmentUpcoming(staffId, page, size);
		
	}
	
	@PutMapping("/appointments/reply")
	public ResponseMessage getUpcomingAppointments(@RequestBody Appointment appointment) {
		return appointmentService.respondtoAppointment(appointment);
		
		
	}


}
