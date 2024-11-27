package com.notification.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.notification.service.NotificationService;
 

/*
 * NotificationController - REST controller to handle notification-related
 * requests.
 * 
 * Author Name: Sameer Gupta 
 * Date: 25-11-2024
 */


@RestController
@RequestMapping("/notification")
public class NotificationController {
	@Autowired
	private NotificationService notificationService;
	
	/*
	 * @param userId the ID of the user
	 * 
	 * @param emaiId the email address to send the notification
	 */
 
	@GetMapping("/sendMail/{userId}")
	public ResponseEntity<String> notifyUser(@PathVariable int userId) {
 
		// Calls the NotificationService to check the user's policy expiry and send a
		// notification.
		String response = notificationService.checkPolicyExpiryAndSendNotification(userId);
 
		// Returns a ResponseEntity with the response and an HTTP status of OK (200).
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
 
}
