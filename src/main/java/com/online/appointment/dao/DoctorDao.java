package com.online.appointment.dao;

import java.util.List;

import com.online.appointment.model.Doctor;

public interface DoctorDao {
	List<Doctor> get();

	Doctor get(int id);

	void save(Doctor doctor);

	void delete(int id);
}
