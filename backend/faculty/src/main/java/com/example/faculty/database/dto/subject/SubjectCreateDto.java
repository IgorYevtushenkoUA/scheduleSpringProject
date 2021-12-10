package com.example.faculty.database.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SubjectCreateDto {
    @NotBlank
    private String name;

    @NotBlank
    private String faculty;

    @NotBlank
    private String speciality;

    @NotNull
    @Min(1)
    @Max(6)
    private int course;

    @NotNull
    private int code;

    @NotNull
    private String trim;
}
