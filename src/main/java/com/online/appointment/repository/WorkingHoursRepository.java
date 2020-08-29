package com.online.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.appointment.model.WorkingHours;

@Repository
public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Integer> {

}
