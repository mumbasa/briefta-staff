package com.briefta.staff.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.Setter;

@RedisHash(value = "QuickMessage")
@Setter
@Getter
public class QuickMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String message;
	private List<ReceptionistMessage> msgs;

	public QuickMessage() {
		// TODO Auto-generated constructor stub
		msgs = new ArrayList<ReceptionistMessage>();
	}
}
