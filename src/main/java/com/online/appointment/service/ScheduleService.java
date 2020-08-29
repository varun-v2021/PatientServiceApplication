package com.online.appointment.service;

import java.util.List;

import com.online.appointment.model.Schedule;

public interface ScheduleService {
	List<Schedule> get();

	Schedule get(int id);

	void save(Schedule doctor);

	void delete(int id);
}
