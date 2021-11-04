package com.briefta.staff.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.briefta.staff.model.Visit;
@Repository
public interface VisitRepository extends JpaRepository<Visit, Long>{

	@Query(value = "SELECT * FROM visit WHERE staff_id=?1 and visit_status=?2 and date(time_in) = curdate()",nativeQuery = true)
	List<Visit> findDayVisitsByStatus(long staffId,String status);

	List<Visit> findVisitsByStaffId(long staffId,Pageable page);
	
	@Query(value = "SELECT * FROM visit WHERE staff_id=?1 and time_in BETWEEN ?2 and ?3",nativeQuery = true)
	Page<Visit> findDayVisitsByStatus(long staffId,LocalDateTime from,LocalDateTime to,Pageable pageable);


}
