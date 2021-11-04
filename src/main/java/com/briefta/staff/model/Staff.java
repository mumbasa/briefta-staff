package com.briefta.staff.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "staff")
	private long id;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String position;
	private String contactInfo;
	private long departmentId;
	private LocalDateTime dateAdded;
	@ManyToOne
	@JoinColumn(name = "departmentId", nullable = false, updatable = false, insertable = false)
	private Department department;
	private int status;
	@Transient
	private String token;

}
