package com.online.appointment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "patients")
public class Patient {
	@Id
	@GeneratedValue
	private Integer id;
	@Column
	String name;
	@Column
	String email;
	@Column
	String phoneNumber;
	@Column
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "UTC")
	Date selectedDate;
	@Column
	String selectedTimeSlot;
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	@OneToOne
	private TimeSlot timeSlot;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public String getSelectedTimeSlot() {
		return selectedTimeSlot;
	}
	public void setSelectedTimeSlot(String selectedTimeSlot) {
		this.selectedTimeSlot = selectedTimeSlot;
	}
	public TimeSlot getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}
}
