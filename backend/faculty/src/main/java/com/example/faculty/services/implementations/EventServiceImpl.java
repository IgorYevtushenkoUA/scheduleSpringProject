package com.example.faculty.services.implementations;

import com.example.faculty.database.entity.Event;
import com.example.faculty.database.repository.EventRepository;
import com.example.faculty.models.requests.EventRequest;
import com.example.faculty.services.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public Event createEvent(EventRequest request) {
        return null;
    }

    @Override
    public Event updateEvent(Long requestId, EventRequest request) {
        return null;
    }

    @Override
    public Event getEventById(Long requestId) {
        return null;
    }

    @Override
    public void deleteEvent(Long requestId) {

    }

    @Override
    public List<Event> getAllEventsByUser(Long userId) {
        return null;
    }

    @Override
    public Page<Event> getAllEvents(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Event> getAllEventsByGroup(String group, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Event> getAllEventsByAuditory(String auditory, Pageable pageable) {
        return null;
    }
}