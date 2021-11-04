package com.briefta.staff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briefta.staff.model.StaffMessage;

public interface StaffMessageRepository extends JpaRepository<StaffMessage, Long> {
	List<StaffMessage> findByStaffId(long staffId);
}
