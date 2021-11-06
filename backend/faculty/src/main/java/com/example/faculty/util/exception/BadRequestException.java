package com.example.faculty.util.exception;

public class BadRequestException extends Throwable {

    public BadRequestException() { super("Bad Request Exception");}

    public BadRequestException(String exception) { super(exception);}
}
