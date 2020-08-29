package com.online.appointment.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.online.appointment.dto.DoctorDTO;
import com.online.appointment.dto.PatientDTO;
import com.online.appointment.engine.ProcessHandler;
import com.online.appointment.model.Patient;
import com.online.appointment.service.PatientService;
import com.online.appointment.utilities.MapperUtils;

@Controller
public class PatientController {
	@Autowired
	private PatientService patientService;

	@Autowired
	private ProcessHandler processHandler;

	@Value("${spring.application.name}")
	String appName;

	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> getPatients() {
		List<Patient> patients = patientService.get();
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}

	@GetMapping("/availableSlots")
	public ResponseEntity<List<String>> getFreeSlots(@RequestParam(name = "dayId") Integer dayId,
			@RequestParam(name = "seldate") String selDate) {		
		List<String> freeSlots = processHandler.computeFreeTimeSlots(dayId, selDate);
		return new ResponseEntity<>(freeSlots, HttpStatus.OK);
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute PatientDTO patientDto, HttpSession session) {
		// sunday is 0, monday 1 ...
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("standardDate", new Date());
		modelAndView.addObject("localDateTime", LocalDateTime.now());
		modelAndView.addObject("localDate", LocalDate.now());
		modelAndView.addObject("timestamp", Instant.now());

		modelAndView.setViewName("user-data");
		modelAndView.addObject("patient", patientDto);

		DoctorDTO doctorDto = (DoctorDTO) session.getAttribute("doctorDto");
		
		MapperUtils mapUtil = new MapperUtils();
		patientService.save(mapUtil.convertToEntity(patientDto, doctorDto));

		return modelAndView;
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ModelAndView book(@ModelAttribute DoctorDTO doctor, HttpSession session) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("booking");
		modelAndView.addObject("doctor", doctor);

		List<String> slots = processHandler.computeAllTimeSlots(doctor.getName());
		modelAndView.addObject("slots", slots);
		
		session.setAttribute("doctorDto", doctor);
		return modelAndView;
	}

}
