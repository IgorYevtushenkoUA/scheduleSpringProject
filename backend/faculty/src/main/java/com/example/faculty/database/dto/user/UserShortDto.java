package com.example.faculty.database.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserShortDto {
    @NotNull
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String parental;

    @NotNull
    private String email;

}
