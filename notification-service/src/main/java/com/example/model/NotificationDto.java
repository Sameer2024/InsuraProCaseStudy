package com.example.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class NotificationDto {
	private int userId;
	private String emailId;
	private String policyName;
	private LocalDate expiryDate;

}
