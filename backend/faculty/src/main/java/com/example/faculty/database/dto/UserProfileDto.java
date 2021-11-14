package com.example.faculty.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class UserProfileDto {
    @NotNull
    @JsonProperty("id")
    private UUID id;

    @Email
    @NotNull
    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("parental")
    private String parental;

    @JsonProperty("about")
    private String about;

    @JsonProperty("course")
    private int course;

    @JsonProperty("faculty")
    private String faculty;
}