package com.online.appointment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.online.appointment.dao.DoctorDao;
import com.online.appointment.dao.PatientDao;
import com.online.appointment.model.Doctor;
import com.online.appointment.model.Patient;
import com.online.appointment.model.Schedule;
import com.online.appointment.model.TimeSlot;
import com.online.appointment.repository.DoctorRepository;
import com.online.appointment.repository.PatientRepository;
import com.online.appointment.repository.ScheduleRepository;
//import com.online.appointment.repository.PatientRepository;
import com.online.appointment.repository.TimeSlotRepository;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	TimeSlotRepository timeSlotRepository;
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	// @Autowired
	// private PatientDao patientDao;

	// @Autowired
	// private DoctorDao doctorDao;

	/*
	 * @Transactional //public Patient savePatient(Patient patient) { public void
	 * savePatient(Patient patient) { //Patient patientRetObj =
	 * patientRepository.save(patient); patientDao.save(patient); //return
	 * patientRetObj; }
	 */

	@Transactional
	public List<Patient> get() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	}

	@Transactional
	public Patient get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@CacheEvict(value = "freeSlots", allEntries = true)
	public void save(Patient patient) {
		
		/*System.out.println("patient slot details: " + patient.getTimeSlot().getSlot() + " " + 
				patient.getTimeSlot().getSchedule().getDay()); */
		
		//TODO: Use the below code to populate timeslot on redirection to booking page
		/*Doctor doctor = doctorRepository.findById(patient.getDoctor().getId()).orElse(null);
		if(null == doctor) {
			doctor = new Doctor();
		}*/
		
		List<Schedule> schedules = scheduleRepository.findAll(); 		
		List<Schedule> filteredSchedules = schedules.stream().filter(s->s.getDay().equals(patient.getTimeSlot().getSchedule().getDay())).collect(Collectors.toList());
		if(filteredSchedules.size() != 1) {
			throw new IllegalStateException("Expected exactly one Schedule but got " + filteredSchedules);
		}
		
		TimeSlot timeSlot = new TimeSlot();
		List<TimeSlot> timeSlots = timeSlotRepository.findSlotsByScheduleId(filteredSchedules.get(0).getId());
		for(TimeSlot ts : timeSlots) {
			if(ts.getSlot().equals(patient.getTimeSlot().getSlot()))
				timeSlot = ts;
		}
		
		
		/*timeSlot.setSlot(patient.getTimeSlot().getSlot());
		timeSlot.setSchedule(filteredSchedules.get(0));
		timeSlotRepository.save(timeSlot);*/
		
		//TODO : remove selected date and timeslot for doctor
		//Doctor doctor = new Doctor();
		//TODO: set the name of doctor
		//doctor.setName(patient.getDoctor().getName());
		System.out.println(">>>>> patient.getSelectedDate() " + patient.getSelectedDate());
		System.out.println(">>>>> patient.getSelectedTimeSlot() " + patient.getSelectedTimeSlot());
		//doctor.setSelectedDate(patient.getSelectedDate());
		//doctor.setSelectedTimeSlot(patient.getSelectedTimeSlot());
		doctorRepository.save(patient.getDoctor());
		
		//patient.setDoctor(doctor);
		patient.setTimeSlot(timeSlot);
		patientRepository.save(patient);
	}

	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Patient> findBySelectedDate(String seldate) {
		return (List<Patient>) patientRepository.findBySelectedDate(seldate);
	}
	
	/*public List<String> getAvailableTimeSlots(){
		List<Doctor> doctors = doctorRepository.findAll();
		
	}*/

	/*
	 * @Transactional public Patient findById(int id) { Patient patientRetObj =
	 * patientRepository.findById(id); return patientRetObj; }
	 * 
	 * @Transactional public List<Patient> findAll() { List<Patient> patients = new
	 * ArrayList<>(); patientRepository.findAll().forEach(patients::add); return
	 * patients; }
	 */

}
