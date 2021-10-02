package com.example.faculty.util.exception;

public class BadCredentialsException extends CustomRuntimeException {
    public BadCredentialsException() {
        super("Not enough rights exception");
    }

    public BadCredentialsException(String message) {
        super(message);
    }
}
