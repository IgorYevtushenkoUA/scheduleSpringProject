package com.example.faculty.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequestDto {
    @NotNull
    @JsonProperty("datetime")
    private Timestamp datetime;

    @NotNull
    @JsonProperty("subject_id")
    private UUID subjectId;

    @NotNull
    @JsonProperty("user_id")
    private UUID userId;

    @NotNull
    @JsonProperty("group")
    private String group;

    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("auditory")
    private String auditory;
}
