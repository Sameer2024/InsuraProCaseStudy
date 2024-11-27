package com.notification.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;

/*
 * NotificationDto - Data Transfer Object (DTO) to hold notification details
 * such as user ID, email, policy name, and expiry date.
 * 
 * Author Name: Sameer Gupta
 *  Date: 25-11-2024
 */

@Data
@Component
public class NotificationDto {

	// Represents the unique identifier for the user associated with the
	// notification.
	@NotNull(message = "User ID cannot be null")
	private int userId;

	// Represents the email address to which the notification will be sent.
	private String emailId;

	// Represents the name of the policy associated with the notification (e.g.,
	// health policy, insurance policy).
	private String policyName;

	// Represents the expiry date of the policy. Used to determine if the
	// notification should be triggered.
	private LocalDate expiryDate;

}
