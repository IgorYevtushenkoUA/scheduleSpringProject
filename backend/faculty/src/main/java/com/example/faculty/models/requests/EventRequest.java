package com.example.faculty.models.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class EventRequest {
    private Long organizer;
    private Long subjectId;
    private Timestamp datetime;
    private String group;
    private String name;
    private String auditory;
    private Boolean isRequest;
}
