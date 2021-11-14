package com.example.faculty.database.mapstruct.mappers;

import com.example.faculty.database.dto.event.EventCreateDto;
import com.example.faculty.database.dto.event.EventResponseDto;
import com.example.faculty.database.dto.event.EventUpdateDto;
import com.example.faculty.database.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IEventMapper {
    IEventMapper MAPPER = Mappers.getMapper(IEventMapper.class);

    @Mapping(source = "userId", target = "creator.id")
    @Mapping(source = "subjectId", target = "subject.id")
    Event createToEvent(EventCreateDto dto);
    @Mapping(source = "userId", target = "creator.id")
    @Mapping(source = "subjectId", target = "subject.id")
    Event updateToEvent(EventUpdateDto dto);
    EventResponseDto eventToResponse(Event event);
}
