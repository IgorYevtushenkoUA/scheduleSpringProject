package com.example.faculty.util.exception;

public class NotEnoughRightsException extends CustomRuntimeException {
    public NotEnoughRightsException() {
        super("Not enough rights exception");
    }
}
