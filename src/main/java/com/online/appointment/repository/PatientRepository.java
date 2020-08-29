package com.online.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.online.appointment.model.Patient;

import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
