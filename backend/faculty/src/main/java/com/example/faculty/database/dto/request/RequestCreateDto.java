package com.example.faculty.database.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RequestCreateDto {

    @NotNull
    @JsonProperty
    private Timestamp time;

    //@NotNull
    @JsonProperty
    private UUID userId;

    @NotNull
    @JsonProperty
    private UUID eventId;

    @NotNull
    @JsonProperty
    private String name;

    @NotNull
    @JsonProperty
    private String group;

    @NotNull
    @JsonProperty
    private String auditory;

    @NotNull
    @JsonProperty
    private Timestamp datetime;

    @NotNull
    @JsonProperty
    private UUID subjectId;
}
