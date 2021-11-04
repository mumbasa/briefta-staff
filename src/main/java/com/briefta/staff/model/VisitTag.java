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

@Entity
@Table
@Getter
@Setter
public class VisitTag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "visit_tag")
	private long id;
	private long tagId;
	private long visitId;
	
	@ManyToOne
	@JoinColumn(name = "tagId",insertable = false,updatable = false)
	private Tag tag;
	
	@ManyToOne
	@JoinColumn(name = "visitId",insertable = false,updatable = false)
	private Visit visit;
}
