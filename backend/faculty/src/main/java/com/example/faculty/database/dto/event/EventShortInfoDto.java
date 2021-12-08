package com.example.faculty.database.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EventShortInfoDto {
    @NotNull
    private UUID id;

    @NotNull
    private String group;

    @NotNull
    private String name;

    @NotNull
    private String auditory;
}
