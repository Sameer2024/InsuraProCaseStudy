package com.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
 * NotificationServiceApplication - Main entry point for the Notification Microservice.
 * 
 * Author Name: Sameer Gupta
 * Date: 25-11-2024
 */

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class NotificationServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	/*
	 * Bean configuration for RestTemplate.
	 * 
	 * returns a new RestTemplate instance
	 */

	@Bean
	RestTemplate getTemplate() {
		return new RestTemplate();
	}

}
