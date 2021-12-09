package com.example.faculty.controller;

import com.example.faculty.database.dto.event.EventCreateDto;
import com.example.faculty.database.dto.event.EventResponseDto;
import com.example.faculty.database.dto.event.EventUpdateDto;
import com.example.faculty.services.interfaces.IEventService;
import com.example.faculty.util.annotations.LogInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")
@CacheConfig(cacheNames = {"events"})
@LogInfo
public class EventController {
    private final IEventService service;

    public EventController(IEventService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventResponseDto> getAll() {
        return service.getAll();
    }

    @Cacheable(key = "#id")
    @GetMapping("/{id}")
    public Optional<EventResponseDto> get(@PathVariable UUID id) {
        System.err.println("call method events");
        return service.get(id);
    }

    @PostMapping("/create")
    public EventResponseDto create(@RequestBody EventCreateDto dto) {
        return service.create(dto);
    }

    @CachePut(key = "#dto.id")
    @PutMapping("/edit")
    public EventResponseDto update(@RequestBody EventUpdateDto dto) {
        return service.update(dto);
    }

    @CacheEvict(key = "#dto.id")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
