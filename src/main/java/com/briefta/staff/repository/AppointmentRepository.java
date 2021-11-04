package com.briefta.staff.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.briefta.staff.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	Page<Appointment> findAppointmentsByStaffIdAndStatus(long staffId,String status,Pageable pageable);
	
	@Query(value ="SELECT * FROM appointment where staff_id=?1 and curdate() <= date(appointment_time)",nativeQuery = true )
	List<Appointment> findAppointmentsUpcomingByStaff(long staffId,Pageable pageable);
	
	List<Appointment> findAppointmentsByCompanyIdAndBookingDate(long companyId,LocalDateTime dateTime,Pageable pageable);

}
