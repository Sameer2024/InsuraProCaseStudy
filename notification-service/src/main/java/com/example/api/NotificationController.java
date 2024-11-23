package com.example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	@Autowired
	private NotificationService notificationService;

	@GetMapping("/sendMail/{userId}")
	public ResponseEntity<String> notifyUser(@PathVariable int userId) {
		String response = notificationService.checkPolicyExpiryAndSendNotification(userId);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
