package com.online.appointment.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.online.appointment.dao.DoctorDao;
import com.online.appointment.model.Doctor;
import com.online.appointment.model.TimeSlot;
import com.online.appointment.repository.DoctorRepository;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorRepository doctorRepository;

	@Override
	//@Cacheable("freeSlots")
	@CacheEvict
	public List<Doctor> get() {
		return doctorRepository.findAll();
	}

	@Override
	public Doctor get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Doctor doctor) {
		doctorRepository.save(doctor);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Doctor> findBySelectedDate(String selDate) {
		return (List<Doctor>) doctorRepository.findBySelectedDate(selDate);
	}
	

}
