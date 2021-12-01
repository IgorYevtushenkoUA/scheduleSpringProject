package com.example.faculty.services.interfaces;

import com.example.faculty.database.dto.event.EventCreateDto;
import com.example.faculty.database.dto.event.EventResponseDto;
import com.example.faculty.database.dto.event.EventUpdateDto;
import com.example.faculty.database.entity.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEventService {
    List<EventResponseDto> getAll();

    Optional<EventResponseDto> get(UUID id);

    void delete(UUID id);

    EventResponseDto create(EventCreateDto dto);

    EventResponseDto update(EventUpdateDto dto);

    List<Event> findEventForUserByYearAndMonthAndDay(int year, int month, int day);

    List<Event> findEventForUserByYearAndMonth(int year, int month);

}