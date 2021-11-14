package com.example.faculty.database.dto.user;

import com.example.faculty.database.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserCreateDto {
    @NotNull
    @JsonProperty
    private String name;

    @NotNull
    @JsonProperty
    private String surname;

    @JsonProperty
    private String parental;

    @NotNull
    @JsonProperty
    private String email;

    @NotNull
    @JsonProperty
    private UserRole role;

    @NotNull
    @JsonProperty
    private String about;

    @NotNull
    @JsonProperty
    private int course;

    @NotNull
    @JsonProperty
    private String faculty;
}
