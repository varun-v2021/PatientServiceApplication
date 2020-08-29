package com.online.appointment.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
public class Schedule  {
	@Id
	@GeneratedValue
	private Integer id;
	@Column
	String day;
	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
	List<TimeSlot> slots = new ArrayList<>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public List<TimeSlot> getSlots() {
		return slots;
	}
	public void setSlots(List<TimeSlot> slots) {
		this.slots = slots;
	}

}
