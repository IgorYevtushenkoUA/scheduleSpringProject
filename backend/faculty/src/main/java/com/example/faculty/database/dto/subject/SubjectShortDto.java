package com.example.faculty.database.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectShortDto {
    @NotNull
    private UUID id;

    @NotNull
    private int code;

    @NotBlank
    private String faculty;

    @NotBlank
    private String name;
}
