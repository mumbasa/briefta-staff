package com.briefta.staff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import com.briefta.staff.model.Appointment;
import com.briefta.staff.model.Department;
import com.briefta.staff.model.Staff;
import com.briefta.staff.model.Visit;
import com.briefta.staff.repository.StaffRepository;

import kong.unirest.Unirest;

@Service
public class MessageService {

	@Value("${mnotify.key}")
	private String smsApiKey;

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	StaffRepository staffRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailContentBuilder mailContentBuilder;

	String sendSms(String message, String contact) {
		return Unirest.get("https://apps.mnotify.net/smsapi").queryString("key", smsApiKey).queryString("to", contact)
				.queryString("msg", message).queryString("sender_id", "BrieftaTech").asString().getBody();
	}

	public void sendNotifyStaff(Object obj) {
		System.err.println(obj.getClass().equals(Appointment.class));
		if (obj.getClass().getSimpleName().contentEquals("Appointment")) {
			Appointment appointment = (Appointment) obj;
			String message = "You have a new appointment from " + appointment.getVisitor().getName();
			jmsTemplate.convertAndSend(appointment.getStaff().getContactInfo(), message);
		} else if (obj.getClass().getSimpleName().contentEquals("Visit")) {
			Visit visit = (Visit) obj;
			String message = "You have a new visit from " + visit.getVisitor().getName();
			jmsTemplate.convertAndSend(visit.getStaff().getContactInfo(), message);
		} else {
			Department department = (Department) obj;
			List<Staff> staffs = staffRepository.findStaffByDepartmentId(department.getId());
			for (Staff staff : staffs) {
				jmsTemplate.convertAndSend(staff.getContactInfo(), "You have a new enquiry");

			}
		}

	}
	
	public void sendNotifyStaff(String address,String message) {
		
				jmsTemplate.convertAndSend(address, message);

		}

	

	public void sendEmail(String subject, String message, List<String> addresses) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(addresses.toArray(String[]::new));
		msg.setSubject(subject);
		msg.setText(message);
		mailSender.send(msg);

	}
	public void sendEmail(String subject, String message, String addresses) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(addresses);
		msg.setSubject(subject);
		msg.setText(message);
		mailSender.send(msg);

	}
	 public void prepareAndSend(Appointment appointment) {
		 System.err.println("sending");
	        MimeMessagePreparator messagePreparator = mimeMessage -> {
	            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
	         //   messageHelper.setFrom("brieftavms@gmail.com");
	         messageHelper.setFrom("brieftavms@gmail.com", "Briefta Technologies");
	            System.out.println(appointment.getEmail());
	            messageHelper.setTo(appointment.getEmail());
	            messageHelper.setSubject("Appointment Decision");
	            String content = mailContentBuilder.build(appointment);
	            messageHelper.setText(content, true);
	        };
	        try {
	            mailSender.send(messagePreparator);
	        } catch (MailException e) {
	        	e.printStackTrace();
	            // runtime exception; compiler will not force you to handle it
	        }
	    }
}
