package com.online.appointment.utilities;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {
	public String formatString(String date) {
		System.out.println("Date to format " + date);
		StringBuilder sb = new StringBuilder();
		String[] dateSplit = date.split("/");
		sb.append(dateSplit[2]);
		sb.append('-');
		sb.append(dateSplit[0]);
		sb.append('-');
		sb.append(dateSplit[1]);
		return sb.toString();
	}
}
