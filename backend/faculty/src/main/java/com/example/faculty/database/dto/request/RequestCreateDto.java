package com.example.faculty.database.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RequestCreateDto {

    @NotNull
    private Timestamp time;

    @NotNull
    private UUID userId;

    @NotNull
    private UUID eventId;

    @NotBlank
    private String name;

    @NotBlank
    private String group;

    @NotBlank
    private String auditory;

    @NotNull
    private Timestamp datetime;

    @NotNull
    private UUID subjectId;
}
