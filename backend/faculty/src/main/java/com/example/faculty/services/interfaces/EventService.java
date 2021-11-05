package com.example.faculty.services.interfaces;

import com.example.faculty.database.entity.Event;
import com.example.faculty.models.requests.EventRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {

    Event createEvent(EventRequest request);

    Event updateEvent(Long requestId, EventRequest request);

    Event getEventById(Long requestId);

    void deleteEvent(Long requestId);

    List<Event> getAllEventsByUser(Long userId);

    Page<Event> getAllEvents(Pageable pageable);

    Page<Event> getAllEventsByGroup(String group, Pageable pageable);

    Page<Event> getAllEventsByAuditory(String auditory, Pageable pageable);
}
