package com.example.faculty.exception;

public class NotFoundException extends CustomRuntimeException {

    public NotFoundException(String message) {
        super( message + " not found");
    }

    public NotFoundException() {
        super("Data not found");
    }
}

