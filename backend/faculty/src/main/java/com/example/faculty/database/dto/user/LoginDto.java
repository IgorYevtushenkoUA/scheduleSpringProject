package com.example.faculty.database.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotNull
    @JsonProperty
    private String username;

    @JsonProperty
    private String password;
}