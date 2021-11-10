package com.example.faculty.services.implementations;

import com.example.faculty.FacultyApplication;
import com.example.faculty.database.entity.Event;
import com.example.faculty.database.repository.EventRepository;
import com.example.faculty.models.requests.EventRequest;
import com.example.faculty.services.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private static final Logger logger = LogManager.getLogger(FacultyApplication.class);
    private static final Marker MARKER_EVENT = MarkerManager.getMarker("EVENT");

    @Autowired
    EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long requestId, EventRequest request) {
        Event event = this.getEventById(requestId);
        event.setName(request.getName());
        event.setAuditory(request.getAuditory());
        event.setGroup(request.getGroup());
        event.setOrganizer(request.getOrganizer());
        event.setRequest(request.getIsRequest());
        event.setSubjectId(request.getSubjectId());
        eventRepository.save(event);
        return event;
    }

    @Override
    public Event getEventById(Long requestId) {
        Event event = eventRepository.findById(requestId).orElse(null);
        MDC.put("event.id", event.getId().toString());
        logger.info(MARKER_EVENT, "MARKER_EVENT test");
        logger.info("Event has -> ID : ");
        MDC.clear();
        return event;
    }

    @Override
    public void deleteEvent(Long requestId) {
        Event event = this.getEventById(requestId);
        eventRepository.delete(event);
    }

    @Override
    public List<Event> getAll() {

        return (List<Event>) eventRepository.findAll();
    }

    @Override
    public List<Event> getAllEventsByUser(Long userId) {
        return null;
    }

    @Override
    public Page<Event> getAllEvents(Pageable pageable) {
        return null;
    }

    public List<Event> getAllEvents() {
        return eventRepository.getAllEvents();
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
