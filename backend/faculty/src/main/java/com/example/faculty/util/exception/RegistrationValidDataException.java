package com.example.faculty.util.exception;

public class RegistrationValidDataException extends Exception {

    public RegistrationValidDataException() {
        super("Registration data exception");
    }

    public RegistrationValidDataException(String message) {
        super(message);
    }
}
