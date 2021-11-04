package com.briefta.staff.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.briefta.staff.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	List<Department> findDepartmentsByCompanyId(long companyId);

	Optional<Department> findDepartmentByDepartmentAndCompanyId(String department, long companyId);
}
