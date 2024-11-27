package com.notification.exception;

/*
 * NotificationDtoException - Custom exception for handling errors related to
 * the NotificationDto class (e.g., validation or processing errors).
 * 
 * Author Name: Sameer Gupta
 * Date: 25-11-2024
 */
public class NotificationDtoException extends RuntimeException {

	// Constructor to initialize the exception with a custom message.

	public NotificationDtoException(String message) {
		super(message);
	}
}
