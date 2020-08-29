package com.online.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.online.appointment.model.Doctor;
import com.online.appointment.model.TimeSlot;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	@Query(value = "SELECT * FROM doctor WHERE selected_date = ?1", nativeQuery = true)
	List<Doctor> findBySelectedDate(String selDate);
}
