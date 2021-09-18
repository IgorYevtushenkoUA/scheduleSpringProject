package com.example.faculty.exception;

public class InvalidFileNameException extends  CustomRuntimeException {
    public InvalidFileNameException() {
        super("Invalid file name");
    }
}
