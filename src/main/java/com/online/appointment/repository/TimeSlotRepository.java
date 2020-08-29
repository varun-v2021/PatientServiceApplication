package com.online.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.online.appointment.model.TimeSlot;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {
	@Query(value = "SELECT * FROM time_slot WHERE fk_schedule_id = ?1", nativeQuery = true)
	List<TimeSlot> findByIdSlots(int id);
}
