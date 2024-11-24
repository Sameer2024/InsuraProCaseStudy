package com.example.exception;

public class PolicyExpiryNotificationException extends RuntimeException {
    public PolicyExpiryNotificationException(String message) {
        super(message);
    }
}
