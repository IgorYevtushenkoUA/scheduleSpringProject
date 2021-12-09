package com.example.faculty.database.dto.user;

import com.example.faculty.database.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserCreateDto {
    @NotBlank
    @Size(min = 3, max = 20)
    @JsonProperty
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @JsonProperty
    private UserRole role = UserRole.STUDENT;

    @NotBlank
    @Size(min = 6, max = 40)
    @JsonProperty
    private String password;

    @NotBlank
    @Size(min = 3, max = 20)
    @JsonProperty
    private String name;

    @NotBlank
    @Size(min = 3, max = 20)
    @JsonProperty
    private String surname;

    @JsonProperty
    private String parental;

    @JsonProperty
    private String about;

    @JsonProperty
    private int course;

    @JsonProperty
    private String faculty;
}
