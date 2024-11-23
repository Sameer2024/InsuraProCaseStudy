package com.example.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.Notification;
import com.example.model.NotificationDto;

import jakarta.mail.internet.MimeMessage;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Notification notification;

	@Value("${spring.mail.username}")
	private String sender;
	private static final int EXPIRY_WARNING_DAYS = 15;

	@Value("${policy.service.url}")
	private String policyServiceUrl;

	private LocalDate expiryDate;
	private String policyName;

	public String sendSimpleMail(Notification notification) {

		try {
			if (notification.getRecipient() == null || notification.getRecipient().isEmpty()) {
				return "Recipient email address is missing or invalid.";
			}

			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(sender);
			helper.setTo(notification.getRecipient());
			helper.setSubject(notification.getSubject());

			String htmlContent = readHtmlTemplate("/templates/emailContext.html");

			htmlContent = htmlContent.replace("${policyName}", policyName);
			htmlContent = htmlContent.replace("${expiryDate}", expiryDate.toString());
			helper.setText(htmlContent, true);

			javaMailSender.send(message);
			return "Mail Sent Successfully...";
		}

		catch (Exception e) {
			return e.getMessage();
		}
	}

	private String readHtmlTemplate(String templatePath) throws Exception {
		try (var inputStream = Objects.requireNonNull(getClass().getResourceAsStream(templatePath))) {
			return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		}
	}

	public String checkPolicyExpiryAndSendNotification(int userId) {
		try {
			String url = policyServiceUrl + userId;
			NotificationDto notificationDto = restTemplate.getForObject(url, NotificationDto.class);

			if (notificationDto == null) {
				return "Policy details not found for user ID " + userId;
			}

			expiryDate = notificationDto.getExpiryDate();
			policyName = notificationDto.getPolicyName();
			long daysUntilExpiry = ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);

			if (daysUntilExpiry <= EXPIRY_WARNING_DAYS) {
				notification.setRecipient(notificationDto.getEmailId());
				notification.setSubject("Policy Expiry Reminder:" + policyName);
				return sendSimpleMail(notification);
			} else {
				return "Policy is not expiring within the next 15 days.";
			}
		} catch (Exception e) {
			return "Error checking policy expiry: " + e.getMessage();
		}
	}

}