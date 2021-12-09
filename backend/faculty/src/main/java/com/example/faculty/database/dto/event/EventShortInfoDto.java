package com.example.faculty.database.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
