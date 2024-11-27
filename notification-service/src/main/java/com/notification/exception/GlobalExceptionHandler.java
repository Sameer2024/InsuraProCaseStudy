package com.notification.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * GlobalExceptionHandler - Handles exceptions globally across the Spring Boot
 * application.
 * 
 * Author Name: Sameer Gupta
 *  Date: 25-11-2024
 */

@ControllerAdvice
public class GlobalExceptionHandler {

	// Handle general exceptions that may occur throughout the application.
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Handle specific exception if policy details are not found
	@ExceptionHandler(PolicyNotFoundException.class)
	public ResponseEntity<String> handlePolicyNotFoundException(PolicyNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	// Handle exceptions related to constraint violations
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
		return new ResponseEntity<>("Constraint violation: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// Handle NullPointerExceptions that may arise during the execution of the
	// application.
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
		return new ResponseEntity<>("Null pointer exception: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// Handle custom exception related to policy expiry notifications
	@ExceptionHandler(PolicyExpiryNotificationException.class)
	public ResponseEntity<String> handlePolicyExpiryNotificationException(PolicyExpiryNotificationException ex) {
		return new ResponseEntity<>("Error sending policy expiry notification", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Handle exceptions related to the NotificationDto class (e.g., validation
	// errors).
	@ExceptionHandler(NotificationDtoException.class)
	public ResponseEntity<String> handleNotificationDtoException(NotificationDtoException ex) {
		return new ResponseEntity<>("Error processing NotificationDto: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
