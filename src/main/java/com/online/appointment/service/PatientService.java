package com.online.appointment.service;

import java.util.List;

import org.springframework.stereotype.Component;
import com.online.appointment.model.Patient;
import com.online.appointment.model.TimeSlot;

@Component
public interface PatientService {
		
	List<Patient> get();

	Patient get(int id);

	void save(Patient patient);

	void delete(int id);
	
	List<Patient> findBySelectedDate(String seldate);

}
