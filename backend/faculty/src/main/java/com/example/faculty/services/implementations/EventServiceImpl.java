package com.example.faculty.services.implementations;

import com.example.faculty.database.entity.Event;
import com.example.faculty.database.repository.EventRepository;
import com.example.faculty.services.implementations.base.BaseServiceImpl;
import com.example.faculty.services.interfaces.IEventService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventServiceImpl extends BaseServiceImpl<Event, UUID> implements IEventService {
    private final EventRepository repository;

    public EventServiceImpl(EventRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
