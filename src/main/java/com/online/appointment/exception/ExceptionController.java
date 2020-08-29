package com.online.appointment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exception(Exception exception) {
		return new ResponseEntity<Object>("Incorrect Information", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
