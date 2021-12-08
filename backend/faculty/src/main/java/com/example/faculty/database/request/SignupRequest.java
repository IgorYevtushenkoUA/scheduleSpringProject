package com.example.faculty.database.request;

import lombok.Data;

import javax.persistence.Lob;
import javax.validation.constraints.*;

@Data
public class SignupRequest {

    @NotNull(message = "Field can't be null!")
    @NotEmpty(message = "Field can't be empty!")
    @Pattern(regexp = "[A-Za-zА-Яа-яєі]*", message = "Not valid name")
    private String name;

    @NotNull(message = "Field can't be null!")
    @NotEmpty(message = "Field can't be empty!")
    @Pattern(regexp = "[A-Za-zА-Яа-яєі]*", message = "Not valid surname")
    private String surname;

    @NotNull(message = "Field can't be null!")
    @NotEmpty(message = "Field can't be empty!")
    @Pattern(regexp = "[A-Za-zА-Яа-яєі]*", message = "Not valid parental")
    private String parental;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

//    private Set<String> role;

    @NotNull(message = "Field can't be null!")
    @NotEmpty(message = "Field can't be empty!")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Not valid user password")
    private String password;

    @NotNull(message = "Field can't be null!")
    @NotEmpty(message = "Field can't be empty!")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Not valid user password")
    private String confirmPassword;

    @Min(1)
    @Max(4)
    private int course;

    @NotNull(message = "Field can't be null!")
    @NotEmpty(message = "Field can't be empty!")
    private String faculty;

    @Lob
    private String about;

}