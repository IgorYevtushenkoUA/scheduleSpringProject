package com.example.faculty.util.exception;

public class InvalidFileNameException extends CustomRuntimeException {
    public InvalidFileNameException() {
        super("Invalid file name");
    }
}
