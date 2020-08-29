package com.online.appointment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.online.appointment.model.TimeSlot;
import com.online.appointment.model.WorkingHours;
import com.online.appointment.repository.TimeSlotRepository;
import com.online.appointment.repository.WorkingHoursRepository;

@Service
@Transactional
public class TimeSlotServiceImpl implements TimeSlotService {
	
	@Autowired
	TimeSlotRepository timeSlotRepository;
	
	@Override
	//@Cacheable("freeSlots")
	@CacheEvict
	public List<TimeSlot> findByIdSlots(int id){
		return (List<TimeSlot>) timeSlotRepository.findByIdSlots(id);
	}
	public List<TimeSlot> get() {
		return timeSlotRepository.findAll();
	}

	@Transactional
	public TimeSlot get(int id) {	
		return timeSlotRepository.findById(id).orElse(null);
	}

	@Transactional
	public void save(TimeSlot timeSlot) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
