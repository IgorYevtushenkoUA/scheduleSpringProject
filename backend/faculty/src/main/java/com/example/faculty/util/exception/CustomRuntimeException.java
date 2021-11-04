package com.example.faculty.util.exception;


public class CustomRuntimeException extends RuntimeException {
    public CustomRuntimeException(String message) {
        super(message, null, false, false);
    }
}
