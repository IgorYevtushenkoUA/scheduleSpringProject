package com.example.faculty.database.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectResponseDto {

    private UUID id;

    private String name;

    private String faculty;

    private String speciality;

    private int course;

    private int code;

    private String trim;
}
