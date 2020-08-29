package com.online.appointment.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.online.appointment.model.Schedule;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService{

	@Override
	public List<Schedule> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Schedule get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Schedule doctor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
