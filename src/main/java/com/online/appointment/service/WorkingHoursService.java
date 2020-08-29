package com.online.appointment.service;

import java.util.List;

import com.online.appointment.model.WorkingHours;

public interface WorkingHoursService {
	List<WorkingHours> get();

	WorkingHours get(int id);

	void save(WorkingHours timeSlot);

	void delete(int id);
}
