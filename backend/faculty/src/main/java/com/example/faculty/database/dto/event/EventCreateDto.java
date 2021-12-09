package com.example.faculty.database.dto.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
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
public class EventCreateDto {
    @NotNull
    private Timestamp datetime;

    @NotNull
    @JsonProperty("subject_id")
    private UUID subjectId;

    @NotNull
    @JsonProperty("user_id")
    private UUID userId;

    @NotBlank
    private String group;

    @NotBlank
    private String name;

    @NotBlank
    private String auditory;
}
