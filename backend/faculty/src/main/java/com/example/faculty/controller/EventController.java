package com.example.faculty.controller;

import com.example.faculty.database.entity.Event;
import com.example.faculty.models.requests.EventRequest;
import com.example.faculty.services.interfaces.EventService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping()
    public Event create(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @PutMapping("{id}")
    public Event update(@PathVariable Long id, @RequestBody EventRequest request) {
        return eventService.updateEvent(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
