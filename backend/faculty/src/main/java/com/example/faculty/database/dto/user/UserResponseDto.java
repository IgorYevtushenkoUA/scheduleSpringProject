package com.example.faculty.database.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private UUID id;

    private String email;

    private String name;

    private String surname;

    private String parental;

    private String about;

    private int course;

    private String faculty;

    private String role;
}
