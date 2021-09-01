package com.marlonfrazao.saladereuniao.controller.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.marlonfrazao.saladereuniao.services.exception.DateFormatException;
import com.marlonfrazao.saladereuniao.services.exception.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;

		return ResponseEntity.status(status).body(getStandardError(e, request, status, "Resource not found!"));
	}
	
	@ExceptionHandler(DateFormatException.class)
	public ResponseEntity<StandardError> dateFormatException(DateFormatException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		return ResponseEntity.status(status).body(getStandardError(e, request, status, "Date format error"));
	}
	
	private StandardError getStandardError(Exception e, HttpServletRequest request, HttpStatus status, String error) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError(error);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return err;
	}
}
