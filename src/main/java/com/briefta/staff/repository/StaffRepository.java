package com.briefta.staff.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.briefta.staff.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

	@Query(value = "SELECT * FROM staff where department_id IN (SELECT id FROM department where company_id=?1)", nativeQuery = true)
	List<Staff> findStaffInCompany(long companyId,Pageable page);
	
	@Query(value = "SELECT * FROM staff where first_name LIKE %?1% or last_name LIKE %?1% or  middle_name LIKE %?1% and department_id IN (SELECT id FROM department where company_id=?2)", nativeQuery = true)
	List<Staff> searchStaff(String name,long companyId,Pageable page);

	Optional<Staff> findStaffBycontactInfo(String contact);
	List<Staff> findStaffByDepartmentId(long departmentId);
	
}
