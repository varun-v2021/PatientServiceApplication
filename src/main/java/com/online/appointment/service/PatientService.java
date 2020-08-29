package com.online.appointment.service;

import java.util.List;

import org.springframework.stereotype.Component;
import com.online.appointment.model.Patient;

@Component
public interface PatientService {
	
	/*public void savePatient(Patient student);
	
	public Patient findById(int id);
	
	public List<Patient> findAll();*/
	
	List<Patient> get();

	Patient get(int id);

	void save(Patient patient);

	void delete(int id);

}
