package com.online.appointment.engine;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.online.appointment.model.Doctor;
import com.online.appointment.model.Patient;
import com.online.appointment.model.TimeSlot;
import com.online.appointment.model.WorkingHours;
import com.online.appointment.service.DoctorService;
import com.online.appointment.service.PatientService;
import com.online.appointment.service.TimeSlotService;
import com.online.appointment.service.WorkingHoursService;
import com.online.appointment.utilities.StringUtils;

@Component
public class ServiceHandler implements ProcessHandler {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private WorkingHoursService workingHoursService;

	@Autowired
	private TimeSlotService timeSlotService;

	@Autowired
	StringUtils stringUtils;

	@Override
	public List<String> computeAllTimeSlots(String doctorName) {
		List<WorkingHours> workingHours = workingHoursService.get();
		List<Doctor> doctorBusyHours = doctorService.get();
		List<String> workingSlots = new ArrayList<>();
		for (WorkingHours wh : workingHours) {
			workingSlots.add(wh.getSlot());
		}
		return workingSlots;
	}

	@Override
	public List<String> computeFreeTimeSlots(int dayId, String patientSelectedDate) {
		List<WorkingHours> workingHours = workingHoursService.get();
		List<TimeSlot> timeSlotsForDay = timeSlotService.findByIdSlots(dayId);
		List<Doctor> doctors = doctorService.findBySelectedDate(stringUtils.formatString(patientSelectedDate));
		List<String> availableSlots = new ArrayList<>();
		List<String> busySlots = new ArrayList<>();
		List<String> workingHoursStr = new ArrayList<>();
		if (doctors.size() > 0) {
			for (Doctor doctor : doctors) {
				if (doctor.getSelectedDate().toString().split(" ")[0]
						.equals(stringUtils.formatString(patientSelectedDate))) {
					System.out.println("Already Booked - " + doctor.getSelectedTimeSlot());
					busySlots.add(doctor.getSelectedTimeSlot());
				}
			}

			for (TimeSlot ts : timeSlotsForDay) {
				workingHoursStr.add(ts.getSlot());
			}

			List<String> union = new ArrayList(workingHoursStr);
			union.addAll(busySlots);
			List<String> intersection = new ArrayList(workingHoursStr);
			intersection.retainAll(busySlots);
			List<Integer> symmetricDifference = new ArrayList(union);
			symmetricDifference.removeAll(intersection);
			availableSlots = new ArrayList(symmetricDifference);

		} else if (doctors.size() == 0 && dayId == 6) {

			List<Patient> patients = patientService.findBySelectedDate(stringUtils.formatString(patientSelectedDate));

			for (Patient patient : patients) {
				System.out.println("Patient booked at " + patient.getSelectedTimeSlot());
				busySlots.add(patient.getSelectedTimeSlot());
			}

			for (TimeSlot ts : timeSlotsForDay) {
				System.out.println("Adding timeslot for the day " + ts.getSlot());
				workingHoursStr.add(ts.getSlot());
			}

			List<String> union = new ArrayList(workingHoursStr);
			union.addAll(busySlots);
			List<String> intersection = new ArrayList(workingHoursStr);
			intersection.retainAll(busySlots);
			List<Integer> symmetricDifference = new ArrayList(union);
			symmetricDifference.removeAll(intersection);
			availableSlots = new ArrayList(symmetricDifference);

		} else {
			for (WorkingHours wh : workingHours) {
				availableSlots.add(wh.getSlot());
			}
		}
		return availableSlots;
	}

}
