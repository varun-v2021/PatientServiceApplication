package com.online.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.appointment.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

}
