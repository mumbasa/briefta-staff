package com.briefta.staff.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table
public class Visitor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "visitor")
	private long id;
	private String name;
	private String company;
	private String telephone;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime dataAdded;
	@Transient
	private long staffid;

}
