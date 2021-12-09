package com.example.faculty.database.dto.calendar;

import com.example.faculty.database.dto.event.EventShortInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CalendarEventDto {

    Map<Integer, List<EventShortInfoDto>> daysAtCalendar;

}
