package com.briefta.staff.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.briefta.staff.model.Visitor;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
	
	Optional<Visitor> findVisitorByTelephone(String contact);
}
