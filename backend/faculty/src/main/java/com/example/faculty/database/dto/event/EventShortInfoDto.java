package com.example.faculty.database.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EventShortInfoDto {
    @NotNull
    private UUID id;

    @NotBlank
    private String group;

    @NotBlank
    private String name;

    @NotBlank
    private String auditory;
}
