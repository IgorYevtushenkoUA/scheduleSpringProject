package com.example.faculty.controller;

import com.example.faculty.database.dto.event.EventCreateDto;
import com.example.faculty.database.dto.event.EventResponseDto;
import com.example.faculty.database.dto.event.EventUpdateDto;
import com.example.faculty.services.interfaces.IEventService;
import com.example.faculty.util.annotations.EvaluateTime;
import com.example.faculty.util.annotations.LogParams;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")
public class EventController {
    private final IEventService service;

    public EventController(IEventService service) {
        this.service = service;
    }

    @EvaluateTime
    @GetMapping
    public List<EventResponseDto> getAll() {
        return service.getAll();
    }

    @LogParams
    @GetMapping("/{id}")
    public Optional<EventResponseDto> get(@PathVariable UUID id) {
        return service.get(id);
    }

    @PostMapping("/create")
    public EventResponseDto create(@RequestBody EventCreateDto dto) {
        return service.create(dto);
    }

    @PutMapping("/edit")
    public EventResponseDto update(@RequestBody EventUpdateDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
