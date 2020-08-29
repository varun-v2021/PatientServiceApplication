package com.online.appointment.model;

//import java.sql.Date;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "doctor")
public class Doctor {
	@Id
	@GeneratedValue
	private Integer id;
	@Column
	String name;
	@OneToMany(mappedBy="doctor", cascade = CascadeType.ALL)
	List<Patient> patients;
	@Column
	//@Temporal(TemporalType.DATE)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "UTC")
	Date selectedDate;
	@Column
	String selectedTimeSlot;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	public Date getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}
	public String getSelectedTimeSlot() {
		return selectedTimeSlot;
	}
	public void setSelectedTimeSlot(String selectedTimeSlot) {
		this.selectedTimeSlot = selectedTimeSlot;
	}
}
