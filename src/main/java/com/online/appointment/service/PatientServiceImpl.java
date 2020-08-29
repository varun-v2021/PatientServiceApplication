package com.online.appointment.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.online.appointment.model.Patient;
import com.online.appointment.model.Schedule;
import com.online.appointment.model.TimeSlot;
import com.online.appointment.repository.DoctorRepository;
import com.online.appointment.repository.PatientRepository;
import com.online.appointment.repository.ScheduleRepository;
import com.online.appointment.repository.TimeSlotRepository;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	TimeSlotRepository timeSlotRepository;

	@Autowired
	ScheduleRepository scheduleRepository;

	@Override
	public List<Patient> get() {
		return patientRepository.findAll();
	}

	@Override
	public Patient get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@CacheEvict(value = "freeSlots", allEntries = true)
	public void save(Patient patient) {

		List<Schedule> schedules = scheduleRepository.findAll();
		List<Schedule> filteredSchedules = schedules.stream()
				.filter(s -> s.getDay().equals(patient.getTimeSlot().getSchedule().getDay()))
				.collect(Collectors.toList());
		if (filteredSchedules.size() != 1) {
			throw new IllegalStateException("Expected exactly one Schedule but got " + filteredSchedules);
		}

		TimeSlot timeSlot = new TimeSlot();
		List<TimeSlot> timeSlots = timeSlotRepository.findSlotsByScheduleId(filteredSchedules.get(0).getId());
		for (TimeSlot ts : timeSlots) {
			if (ts.getSlot().equals(patient.getTimeSlot().getSlot()))
				timeSlot = ts;
		}

		doctorRepository.save(patient.getDoctor());

		patient.setTimeSlot(timeSlot);
		patientRepository.save(patient);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Patient> findBySelectedDate(String seldate) {
		return (List<Patient>) patientRepository.findBySelectedDate(seldate);
	}

}
