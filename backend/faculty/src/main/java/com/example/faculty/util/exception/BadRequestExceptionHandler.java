package com.example.faculty.util.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {BadRequestException.class})
    protected ResponseEntity<Object> handleConflict(IllegalStateException e, WebRequest req) {

        ApiException apiException = ApiException
                .builder()
                .message(e.getCause().getMessage())
                .description(e.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();

        return handleExceptionInternal(
                e,
                apiException,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                req);
    }
}
