package com.online.appointment.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.online.appointment.model.WorkingHours;
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
