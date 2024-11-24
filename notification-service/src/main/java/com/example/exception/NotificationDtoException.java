package com.example.exception;

public class NotificationDtoException extends RuntimeException {
    public NotificationDtoException(String message) {
        super(message);
    }
}
