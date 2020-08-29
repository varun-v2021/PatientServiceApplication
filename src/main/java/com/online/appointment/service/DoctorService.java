package com.online.appointment.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.online.appointment.model.Doctor;

@Component
public interface DoctorService {
	
	List<Doctor> get();

	Doctor get(int id);

	void save(Doctor doctor);

	void delete(int id);
	
	List<Doctor> findBySelectedDate(String selDate);

}
