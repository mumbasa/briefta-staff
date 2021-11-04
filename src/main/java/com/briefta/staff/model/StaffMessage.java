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

public class StaffMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "staffmessage")
	private long id;
	private String message;
	private long staffId;
	private LocalDateTime date;
    private long companyId;	
	@ManyToOne
	@JoinColumn(name = "staffId",updatable = false,insertable = false)
	private Staff staff;
}
