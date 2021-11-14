package com.example.faculty.database.dto.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SubjectCreateDto {
    @NotNull
    @JsonProperty
    private String name;

    @NotNull
    @JsonProperty
    private String faculty;

    @NotNull
    @JsonProperty
    private String speciality;

    @NotNull
    @Min(1)
    @Max(4)
    @JsonProperty
    private int course;

    @NotNull
    @JsonProperty
    private int code;

    @NotNull
    @JsonProperty
    private String trim;
}
