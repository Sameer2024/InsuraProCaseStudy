package com.notification.exception;

/*
 * PolicyExpiryNotificationException - Custom exception for handling errors related to
 * policy expiry notifications.
 * 
 * Author Name: Sameer Gupta
 * Date: 25-11-2024
 */
public class PolicyExpiryNotificationException extends RuntimeException {

	// Constructor to initialize the exception with a custom message.

	public PolicyExpiryNotificationException(String message) {
		super(message);
	}
}
