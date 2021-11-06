package com.example.faculty.database.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CreateSubjectDto {

    @NotNull
    private String name;

    @NotNull
    private String faculty;

    @NotNull
    private String speciality;

    @Min(1)
    @Max(4)
    @NotNull
    private int course;

    @NotNull
    private int code;

    @NotNull
    private String trim;

}
