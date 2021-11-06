package com.example.faculty.util.exception;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@JsonAutoDetect
public class ApiException {
    private String message;
    private String description;
    private HttpStatus httpStatus;
}
