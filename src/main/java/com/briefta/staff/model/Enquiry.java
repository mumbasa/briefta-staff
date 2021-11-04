package com.briefta.staff.model;

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

@Entity
@Table
@Setter
@Getter
public class Enquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "enquiry")
	private long id;
	private String email;
	private String status;
	private String enquiry;
	private LocalDateTime answeredDate;
	private LocalDateTime enquiryDate;
	private String reply;
	private long staffId;
	private long departmentId;
	private long visitorId;

	@ManyToOne
	@JoinColumn(name = "departmentId", nullable = false, updatable = false, insertable = false)
	private Department department;
	@ManyToOne
	@JoinColumn(name = "visitorId", updatable = false, insertable = false)
	private Visitor visitor;
	@ManyToOne
	@JoinColumn(name = "staffId", updatable = false, insertable = false)
	private Staff staff;

}
