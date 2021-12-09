package com.example.faculty.database.dto.event;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EventInfoDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String group;

    @NotBlank
    private String name;

    @NotBlank
    private String auditory;

    @NotBlank
    private String subject;

    @NotNull
    private int hours;

    @NotNull
    private int minutes;

}
