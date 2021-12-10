package com.example.faculty.services.interfaces;

import com.example.faculty.database.entity.Attendee;

import java.util.List;
import java.util.UUID;

public interface IAttendeeService {
    List<Attendee> getAll();

    void delete(UUID id);

    Attendee create(Attendee dto);

    Attendee update(Attendee dto);

    Attendee deleteByUserAndEvent(UUID userUUId, UUID eventUUID);

    Attendee getByUserAndEvent(UUID userUUId, UUID eventUUID);

}
