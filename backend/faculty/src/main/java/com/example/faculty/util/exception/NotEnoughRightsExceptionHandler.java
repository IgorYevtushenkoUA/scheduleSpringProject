package com.example.faculty.util.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class NotEnoughRightsExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {NotEnoughRightsException.class})
    protected ResponseEntity<Object> handleConflict(IllegalStateException e, WebRequest req) {

        ApiException apiException = ApiException
                .builder()
                .message(e.getCause().getMessage())
                .description(e.getMessage())
                .httpStatus(HttpStatus.FORBIDDEN)
                .build();

        return handleExceptionInternal(
                e,
                apiException,
                new HttpHeaders(),
                HttpStatus.FORBIDDEN,
                req);
    }
}
