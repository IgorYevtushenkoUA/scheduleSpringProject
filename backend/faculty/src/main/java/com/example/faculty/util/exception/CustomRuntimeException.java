package com.example.faculty.util.exception;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomRuntimeException extends RuntimeException {
    public CustomRuntimeException(String message) {
        super(message, null, false, false);
        log.info("Exception - " + message);
    }
}
