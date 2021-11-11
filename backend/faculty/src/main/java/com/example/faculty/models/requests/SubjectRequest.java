package com.example.faculty.models.requests;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubjectRequest {

    private String name;
    private String faculty;
    private String speciality;
    private String trim;
    private int course;
    private String code;
}
