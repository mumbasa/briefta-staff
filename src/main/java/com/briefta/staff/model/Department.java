package com.briefta.staff.model;

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
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "department")
	private long id;
	private String department;
	private long companyId;
	private String email;
	@ManyToOne
	@JoinColumn(name ="companyId",insertable = false,updatable = false)
	private Company company;
}
