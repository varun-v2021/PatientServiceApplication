package com.online.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.online.appointment.model.Patient;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	@Query(value = "SELECT * FROM patients WHERE selected_date = ?1", nativeQuery = true)
	List<Patient> findBySelectedDate(String seldate);
}
