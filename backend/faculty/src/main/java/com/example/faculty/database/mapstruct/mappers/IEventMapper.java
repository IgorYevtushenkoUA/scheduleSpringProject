package com.example.faculty.database.mapstruct.mappers;

import com.example.faculty.database.dto.EventRequestDto;
import com.example.faculty.database.dto.EventResponseDto;
import com.example.faculty.database.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IEventMapper {
    IEventMapper MAPPER = Mappers.getMapper(IEventMapper.class);

    Event requestToEvent(EventRequestDto dto);
    EventResponseDto eventToResponse(Event event);
}
