package com.briefta.staff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.briefta.staff.model.Appointment;
import com.briefta.staff.model.ResponseMessage;
import com.briefta.staff.repository.AppointmentRepository;

@Service
public class AppointmentService {
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	MessageService messageService;

	public ResponseMessage getAppointment(long staffId, int page, int size, String status) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Appointment> apps = appointmentRepository.findAppointmentsByStaffIdAndStatus(staffId, status, pageable);
		ResponseMessage message  = new ResponseMessage();
		message.setMessage("Records Found : "+apps.getTotalElements());
		message.setStatusCode(200);
		message.setPayload(apps.get());
		return message;
	}
	
	public ResponseMessage getAppointmentUpcoming(long staffId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		List<Appointment> apps = appointmentRepository.findAppointmentsUpcomingByStaff(staffId,  pageable);
		ResponseMessage message  = new ResponseMessage();
		message.setMessage("Records Found : "+apps.size());
		message.setStatusCode(200);
		message.setPayload(apps);
		return message;
	}
	
	public ResponseMessage respondtoAppointment(Appointment appointment) {
		Appointment ap = appointmentRepository.findById(appointment.getId()).get();
		ap.setAppointmentTime(appointment.getAppointmentTime());
		ap.setStatus(appointment.getStatus());
		ResponseMessage message  = new ResponseMessage();
		message.setMessage(appointment.getMessage());
		message.setStatusCode(200);
		ap=appointmentRepository.save(ap);
		message.setPayload(ap);
		messageService.prepareAndSend(ap);
		return message;
	}

}
