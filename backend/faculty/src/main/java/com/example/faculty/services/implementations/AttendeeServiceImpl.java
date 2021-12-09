package com.example.faculty.services.implementations;

import com.example.faculty.database.entity.Attendee;
import com.example.faculty.database.repository.AttendeeRepository;
import com.example.faculty.services.interfaces.IAttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttendeeServiceImpl implements IAttendeeService {

    private final AttendeeRepository repository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.repository = attendeeRepository;
    }

    @Override
    public List<Attendee> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Attendee create(Attendee attendee) {
        return repository.save(attendee);
    }

    @Override
    public Attendee update(Attendee attendee) {
        return repository.save(attendee);
    }

    @Override
    public Attendee deleteByUserAndEvent(UUID userUUId, UUID eventUUID) {
        return repository.deleteByUserAndEvent(userUUId, eventUUID);
    }

    @Override
    public Attendee getByUserAndEvent(UUID userUUId, UUID eventUUID) {
        return repository.getByUserAndEvent(userUUId, eventUUID);
    }
}
