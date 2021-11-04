package com.briefta.staff.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.briefta.staff.model.QuickMessage;

@Repository
public interface QuickMessageRepository extends CrudRepository<QuickMessage, Long> {
	Iterable<QuickMessage> findByReceiver(long receiver);
	Iterable<QuickMessage> findByMessage(String msg);
}
