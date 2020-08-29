package com.online.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.online.appointment.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

}
