package com.briefta.staff.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table
@Entity
public class Appointment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "appointment")
	private long id;
	private String email;
	private String status;
	private String purpose;
	private LocalDateTime appointmentTime;
	private LocalDateTime bookingDate;
	private String message;
	private long staffId;
	private String title;
	private long visitorId;
	private long companyId;
	
	@ManyToOne
	@JoinColumn(name = "staffId",updatable = false,insertable = false)
	private Staff staff;
	
	@ManyToOne
	@JoinColumn(name = "visitorId",updatable = false,insertable = false)
	private Visitor visitor;
	
	
	@ManyToOne
	@JoinColumn(name = "companyId",updatable = false,insertable = false)
	private Company company;

}
