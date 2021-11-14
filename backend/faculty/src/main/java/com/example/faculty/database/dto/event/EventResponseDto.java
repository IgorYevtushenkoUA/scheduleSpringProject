package com.example.faculty.database.dto.event;

import com.example.faculty.database.dto.subject.SubjectShortDto;
import com.example.faculty.database.dto.user.UserShortDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponseDto {
    @NotNull
    private UUID id;

    @NotNull
    private Timestamp datetime;

    @NotNull
    private String group;

    @NotNull
    private String name;

    @NotNull
    private String auditory;

    @NotNull
    private UserShortDto user;

    @NotNull
    private SubjectShortDto subject;
}
