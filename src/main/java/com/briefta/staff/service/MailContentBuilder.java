package com.briefta.staff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.briefta.staff.model.Appointment;
import com.briefta.staff.model.Enquiry;

@Service
public class MailContentBuilder {
	@Autowired
	private TemplateEngine templateEngine;

	
	@Autowired
	public MailContentBuilder(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	public String build(Appointment appointment) {
		Context context = new Context();
		context.setVariable("message", appointment.getMessage());
		context.setVariable("staffName",
				appointment.getStaff().getFirstName() + " " + appointment.getStaff().getLastName());
		context.setVariable("time", appointment.getAppointmentTime().toLocalTime().toString());
		context.setVariable("date", appointment.getAppointmentTime().toLocalDate().toString());
		context.setVariable("decision", appointment.getStatus());
		context.setVariable("visitor", appointment.getVisitor().getName());
		context.setVariable("company", appointment.getCompany().getCompanyName());
		return templateEngine.process("ping", context);
	}

	public String build(Enquiry enquiry) {
		Context context = new Context();
		context.setVariable("message", enquiry.getEnquiry());
		context.setVariable("staffName", enquiry.getDepartment().getDepartment());
		context.setVariable("time", enquiry.getEnquiryDate().toLocalTime());
		context.setVariable("date", enquiry.getEnquiryDate().toLocalDate().toString());
		context.setVariable("decision", enquiry.getStatus());
		context.setVariable("visitor", enquiry.getVisitor().getName());
		context.setVariable("company", enquiry.getDepartment().getCompany().getCompanyName());
		return templateEngine.process("ping", context);
	}

}
