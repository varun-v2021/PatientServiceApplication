package com.online.appointment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
public class WorkingHoursServiceImpl implements WorkingHoursService {

	@Autowired
	WorkingHoursRepository workingHoursRepository;

	@Override
	@CacheEvict
	public List<WorkingHours> get() {
		return workingHoursRepository.findAll();
	}

	@Override
	public WorkingHours get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(WorkingHours timeSlot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
