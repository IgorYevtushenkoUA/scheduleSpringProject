package com.example.faculty.services.interfaces;

import com.example.faculty.database.dto.request.RequestCreateDto;
import com.example.faculty.database.dto.request.RequestResponseDto;
import com.example.faculty.database.dto.request.RequestUpdateDto;
import com.example.faculty.database.entity.Attendee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IAttendeeService {
    List<Attendee> getAll();

    void delete(UUID id);

    Attendee create(Attendee dto);

    Attendee update(Attendee dto);

}
