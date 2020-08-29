package com.online.appointment.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class PatientDTO {
	@NotNull
	String name;
	@NotNull
	String email;
	@NotNull
	String phone;
	@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	Date apptdate;
	@NotNull
	String selectedSlot;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getApptdate() {
		return apptdate;
	}

	public void setApptdate(Date apptdate) {
		this.apptdate = apptdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSelectedSlot() {
		return selectedSlot;
	}

	public void setSelectedSlot(String selectedSlot) {
		this.selectedSlot = selectedSlot;
	}
}
