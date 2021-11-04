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
@Setter
@Getter
@Entity
@Table
public class Visit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "visit")
	private long id;
	private LocalDateTime timeIn;
	private LocalDateTime timeOut;
	private String mission;
	private long staffId;
	private long companyId;
	private String carNumber;
	private long visitorId;
	private String picture;
	private String visitStatus;
	@ManyToOne
	@JoinColumn(name = "visitorId",updatable = false,nullable = true,insertable = false)
	private Visitor visitor;
	
	
	@ManyToOne
	@JoinColumn(name = "staffId",nullable = true,updatable = false,insertable = false)
	private Staff staff;
}
