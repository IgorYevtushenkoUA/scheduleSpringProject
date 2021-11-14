package com.example.faculty.services.interfaces;

import com.example.faculty.database.dto.EventRequestDto;
import com.example.faculty.database.dto.EventResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEventService {
    List<EventResponseDto> getAll();
    Optional<EventResponseDto> get(UUID id);
    void delete(UUID id);
    EventResponseDto create(EventRequestDto dto);
    EventResponseDto update(EventRequestDto dto);
}
