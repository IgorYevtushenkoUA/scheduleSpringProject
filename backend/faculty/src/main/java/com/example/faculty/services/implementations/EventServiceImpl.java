package com.example.faculty.services.implementations;

import com.example.faculty.database.dto.EventRequestDto;
import com.example.faculty.database.dto.EventResponseDto;
import com.example.faculty.database.entity.Event;
import com.example.faculty.database.mapstruct.mappers.IEventMapper;
import com.example.faculty.database.repository.EventRepository;
import com.example.faculty.services.interfaces.IEventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements IEventService {
    private final EventRepository repository;

    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EventResponseDto> getAll() {
        return repository.findAll().stream().map(IEventMapper.MAPPER::eventToResponse).collect(Collectors.toList());
    }

    @Override
    public Optional<EventResponseDto> get(UUID id) {
        return repository.findById(id).map(IEventMapper.MAPPER::eventToResponse);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public EventResponseDto create(EventRequestDto dto) {
        Event event = IEventMapper.MAPPER.requestToEvent(dto);
        return IEventMapper.MAPPER.eventToResponse(repository.save(event));
    }

    @Override
    public EventResponseDto update(EventRequestDto dto) {
        Event event = IEventMapper.MAPPER.requestToEvent(dto);
        return IEventMapper.MAPPER.eventToResponse(repository.save(event));
    }
}
