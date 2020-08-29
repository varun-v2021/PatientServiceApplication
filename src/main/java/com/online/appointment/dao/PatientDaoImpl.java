package com.online.appointment.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.online.appointment.model.Patient;

@Repository
public class PatientDaoImpl implements PatientDao {

	@Autowired
	EntityManager entityManager;

	@Override
	public List<Patient> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Patient patient) {
		Session currSession = entityManager.unwrap(Session.class);
		currSession.saveOrUpdate(patient);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
