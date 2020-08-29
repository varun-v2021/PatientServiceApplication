package com.online.appointment.utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.http.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.online.appointment.dto.DoctorDTO;
import com.online.appointment.dto.PatientDTO;
import com.online.appointment.model.Doctor;
import com.online.appointment.model.Patient;
import com.online.appointment.model.Schedule;
import com.online.appointment.model.TimeSlot;

public class MapperUtils {

	public Patient convertToEntity(PatientDTO patientDto, DoctorDTO doctorDto) throws ParseException {
		ModelMapper modelMapper = new ModelMapper();
		Patient patient = modelMapper.map(patientDto, Patient.class);

		Map<Integer, String> workingDays = new HashMap<Integer, String>();
		workingDays.put(0, "Sunday");
		workingDays.put(1, "Monday");
		workingDays.put(2, "Tuesday");
		workingDays.put(3, "Wednesday");
		workingDays.put(4, "Thursday");
		workingDays.put(5, "Friday");
		workingDays.put(6, "Saturday");
		Optional<String> day = workingDays.entrySet().stream()
				.filter(e -> patientDto.getApptdate().getDay() == e.getKey()).map(Map.Entry::getValue).findFirst();

		System.out.println("Patient Name: " + patientDto.getName());
		System.out.println("Patient Email: " + patientDto.getEmail());
		System.out.println("Patient Phone: " + patientDto.getPhone());
		System.out.println("Appointment Time: " + patientDto.getApptdate().getTime());
		System.out.println("Selected Slot : " + patientDto.getSelectedSlot());
		// sunday is 0, monday 1 ...
		System.out.println("Appointment Date: " + patientDto.getApptdate().getDay());
		System.out.println("Appointment Day: " + day.get());

		Schedule schedule = new Schedule();
		schedule.setId(patientDto.getApptdate().getDay());
		schedule.setDay(day.get());

		patient.setName(patientDto.getName());
		patient.setEmail(patientDto.getEmail());
		patient.setPhoneNumber(patientDto.getPhone());
		patient.setSelectedDate(patientDto.getApptdate());

		Doctor doctor = new Doctor();
		// TODO: To dynamically populate id
		System.out.println("<<<<<< patientDto.getSelectedSlot() " + patientDto.getSelectedSlot());
		System.out.println("<<<<<< patientDto.getApptdate() " + patientDto.getApptdate());
		//doctor.setId(1);
		doctor.setName(doctorDto.getName());
		doctor.setSelectedDate(patientDto.getApptdate());
		doctor.setSelectedTimeSlot(patientDto.getSelectedSlot());
		patient.setDoctor(doctor);

		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setSlot(patientDto.getSelectedSlot());
		timeSlot.setSchedule(schedule);

		patient.setTimeSlot(timeSlot);
		patient.setSelectedTimeSlot(patientDto.getSelectedSlot());

		/*
		 * post.setSubmissionDate(
		 * postDto.getSubmissionDateConverted(userService.getCurrentUser().getPreference
		 * ().getTimezone()));
		 * 
		 * if (postDto.getId() != null) { Post oldPost =
		 * postService.getPostById(postDto.getId());
		 * post.setRedditID(oldPost.getRedditID()); post.setSent(oldPost.isSent()); }
		 */
		return patient;
	}
}
