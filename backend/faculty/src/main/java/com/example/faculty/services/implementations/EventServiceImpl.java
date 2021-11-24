package com.example.faculty.services.implementations;

import com.example.faculty.database.dto.event.EventCreateDto;
import com.example.faculty.database.dto.event.EventResponseDto;
import com.example.faculty.database.dto.event.EventUpdateDto;
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
    public EventResponseDto create(EventCreateDto dto) {
        Event event = IEventMapper.MAPPER.createToEvent(dto);
        return IEventMapper.MAPPER.eventToResponse(repository.save(event));
    }

    @Override
    public EventResponseDto update(EventUpdateDto dto) {
        Event event = IEventMapper.MAPPER.updateToEvent(dto);
        return IEventMapper.MAPPER.eventToResponse(repository.save(event));
    }

    @Override
    public List<Event> findEventForUserByYearAndMonthAndDay(int year, int month, int day) {
        return repository.findEventForUserByYearAndMonthAndDay(year, month, day);
    }

    @Override
    public List<Event> findEventForUserByYearAndMonth(int year, int month) {
        return repository.findEventForUserByYearAndMonth(year, month);
    }

}
