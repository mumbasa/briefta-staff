package com.briefta.staff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.briefta.staff.model.ReceptionistMessage;
@Repository
public interface ReceptionistMessageRepository extends JpaRepository<ReceptionistMessage, Long> {
	List<ReceptionistMessage> findAllByCompanyId(long id);
}
