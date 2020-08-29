package com.online.appointment.engine;

import java.util.List;

public interface ProcessHandler {
	public List<String> computeAllTimeSlots(String doctorName);
	public List<String> computeFreeTimeSlots(int dayId, String date);
}
