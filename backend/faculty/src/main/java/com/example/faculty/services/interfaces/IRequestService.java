package com.example.faculty.services.interfaces;

import com.example.faculty.database.dto.request.RequestCreateDto;
import com.example.faculty.database.dto.request.RequestResponseDto;
import com.example.faculty.database.dto.request.RequestUpdateDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRequestService {

    List<RequestResponseDto> getAll();

    Optional<RequestResponseDto> get(UUID id);

    void delete(UUID id);

    RequestResponseDto create(RequestCreateDto dto);

    RequestResponseDto update(RequestUpdateDto dto);

    void deleteByEvent(UUID id);

}
