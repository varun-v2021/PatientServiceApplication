package com.online.appointment.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.online.appointment.model.Doctor;

public class DoctorDaoImpl implements DoctorDao {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Doctor> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Doctor doctor) {
		Session currSession = entityManager.unwrap(Session.class);
		currSession.saveOrUpdate(doctor);		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
