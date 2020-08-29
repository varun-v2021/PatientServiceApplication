package com.online.appointment.dto;

import javax.validation.constraints.NotNull;

public class DoctorDTO {
	int id;
	@NotNull
	String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
