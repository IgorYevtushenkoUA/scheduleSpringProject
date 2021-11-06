package com.example.faculty.database.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateEventDto {
    @NotNull
    private long organizer;

    @NotNull
    private long subjectId;

    @NotNull
    private String group;

    @NotNull
    private String name;

    @NotNull
    private String auditory;

    @NotNull
    private boolean isRequest;
}
