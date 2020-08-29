package com.online.appointment.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.online.appointment.model.TimeSlot;

@Component
public interface TimeSlotService {
	List<TimeSlot> get();

	TimeSlot get(int id);

	void save(TimeSlot timeSlot);

	void delete(int id);
	
	List<TimeSlot> findByIdSlots(int id);
}
