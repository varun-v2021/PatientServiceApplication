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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.online.appointment.dto.DoctorDTO;
import com.online.appointment.dto.PatientDTO;
import com.online.appointment.engine.ProcessHandler;
import com.online.appointment.model.Patient;
import com.online.appointment.model.TimeSlot;
import com.online.appointment.model.WorkingHours;
import com.online.appointment.service.PatientService;
import com.online.appointment.service.TimeSlotService;
import com.online.appointment.service.WorkingHoursService;
import com.online.appointment.utilities.MapperUtils;

@Controller
//@RestController
//@RequestMapping("/api")
public class PatientController {
	@Autowired
	private PatientService patientService;

	@Autowired
	private WorkingHoursService workingHoursService;

	@Autowired
	private TimeSlotService timeSlotService;

	@Autowired
	private ProcessHandler processHandler;

	@Value("${spring.application.name}")
	String appName;

	/*
	 * @RequestMapping(value = "/patient", method = RequestMethod.POST) public
	 * ModelAndView save(@RequestParam("patName") String
	 * patientName, @RequestParam("patEmail") String patientEmail,
	 * 
	 * @RequestParam("patPhone") String patientPhoneNo) { Patient patient = new
	 * Patient(); patient.setName(patientName); patient.setEmail(patientEmail);
	 * patient.setPhoneNumber(patientPhoneNo); patientService.save(patient); return
	 * new ModelAndView("success"); }
	 */

	/*
	 * @RequestMapping(value = "/patient", method = RequestMethod.POST) public
	 * ModelAndView processRequest(@ModelAttribute("pat") Patient patient) {
	 * patientService.save(patient); return new ModelAndView("success"); }
	 */

	@PostMapping("/patient")
	public Patient save(@RequestBody Patient patient) {
		patientService.save(patient);
		return patient;
	}

	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> getPatients() {
		List<Patient> patients = patientService.get();
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}

	@GetMapping("/availableSlots")
	public ResponseEntity<List<String>> getFreeSlots(@RequestParam(name = "dayId") Integer dayId,
			@RequestParam(name = "seldate") String selDate) {
		System.out.println("---------- seldate " + selDate);
		List<TimeSlot> unscheduledHours = timeSlotService.findByIdSlots(dayId);
		
		List<String> freeSlots = processHandler.computeFreeTimeSlots(dayId, selDate);
		return new ResponseEntity<>(freeSlots, HttpStatus.OK);
	}

	// http://localhost:8082/patients/test
	// http://localhost:8082/appointment/api/
	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String
	 * homePage(Model model) { model.addAttribute("appName", appName); return
	 * "home"; }
	 */

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	// @ResponseBody
	/*
	 * public String savePatient(@RequestParam(name = "name") String name,
	 * 
	 * @RequestParam(name = "email") String email,
	 * 
	 * @RequestParam(name = "phoneNumber") String phoneNumber) { //Patient
	 * patientResponse = (Patient) patientService.savePatient(patient);
	 * /*System.out.println("name: " + name); System.out.println("email: " + email);
	 * //System.out.println("phoneNumber: " + phoneNumber); return "success";
	 */
	public ModelAndView save(@ModelAttribute PatientDTO patientDto, HttpSession session) {

		System.out.println("Patient Name: " + patientDto.getName());
		System.out.println("Patient Email: " + patientDto.getEmail());
		System.out.println("Patient Phone: " + patientDto.getPhone());
		System.out.println("Appointment Time: " + patientDto.getApptdate().getTime());
		System.out.println("Selected Slot : " + patientDto.getSelectedSlot());
		// sunday is 0, monday 1 ...
		System.out.println("Appointment Date: " + patientDto.getApptdate().getDay());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("standardDate", new Date());
		modelAndView.addObject("localDateTime", LocalDateTime.now());
		modelAndView.addObject("localDate", LocalDate.now());
		modelAndView.addObject("timestamp", Instant.now());

		modelAndView.setViewName("user-data");
		modelAndView.addObject("patient", patientDto);

		DoctorDTO doctorDto = (DoctorDTO) session.getAttribute("doctorDto");
		System.out.println("Doctor Name.......: " + doctorDto.getName());

		MapperUtils mapUtil = new MapperUtils();
		patientService.save(mapUtil.convertToEntity(patientDto, doctorDto));

		return modelAndView;
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ModelAndView book(@ModelAttribute DoctorDTO doctor, HttpSession session) {
		System.out.println("Doctor Name: " + doctor.getName());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("booking");
		modelAndView.addObject("doctor", doctor);
		// TODO: addObject for timeslots here and retrieve it in booking page
		List<String> slots = processHandler.computeAllTimeSlots(doctor.getName());
		modelAndView.addObject("slots", slots);
		System.out.println("Slots size : " + slots.size());
		session.setAttribute("doctorDto", doctor);
		return modelAndView;
	}

	/*
	 * @RequestMapping(method = RequestMethod.GET)
	 * 
	 * @ResponseBody public List<Patient> getPatients(@RequestBody Patient patient)
	 * { List<Patient> patientsList = patientService.findAll(); return patientsList;
	 * }
	 * 
	 * @RequestMapping(value = "/add", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public Patient addPatient(@RequestBody Patient patient) {
	 * Patient patientResponse = (Patient) patientService.savePatient(patient);
	 * return patientResponse; }
	 */
}
