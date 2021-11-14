package com.example.faculty.database.dto.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class SubjectUpdateDto extends SubjectCreateDto {
    @NotNull
    @JsonProperty
    private UUID id;
}
