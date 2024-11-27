package com.notification.service;

/*
 * NotificationService - This interface defines the contract for sending policy expiry notifications.
 * 
 * Author Name: Sameer Gupta
 * Date: 25-11-2024
 */

public interface NotificationService {

	/*
	 * Method to check if a user's policy has expired and send a notification
	 * accordingly.
	 * 
	 * @param userId the ID of the user
	 * 
	 * @param emaiId the email address to send the notification
	 */
	String checkPolicyExpiryAndSendNotification(int userId);

}
