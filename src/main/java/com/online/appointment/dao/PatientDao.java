package com.online.appointment.dao;

import java.util.List;

import com.online.appointment.model.Patient;

public interface PatientDao {
	List<Patient> get();

	Patient get(int id);

	void save(Patient patient);

	void delete(int id);
}
