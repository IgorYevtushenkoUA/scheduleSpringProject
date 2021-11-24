package com.example.faculty.database.dto.event;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EventShortInfoDto {

    private String group;
    private String name;
    private String auditory;
    private String subject;
    private int hours;
    private int minutes;

}
