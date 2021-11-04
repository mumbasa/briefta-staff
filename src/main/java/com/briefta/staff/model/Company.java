package com.briefta.staff.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table
@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "company")
	private long id;
	private String companyName;
	private String email;
	private String companyCode;
	private String contact;
	private int status;
	private LocalDateTime dateAdded;
	private String companyLogo;


}
