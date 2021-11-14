package com.example.faculty.services.interfaces;

import com.example.faculty.database.dto.subject.SubjectCreateDto;
import com.example.faculty.database.dto.subject.SubjectResponseDto;
import com.example.faculty.database.dto.subject.SubjectUpdateDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISubjectService {
    List<SubjectResponseDto> getAll();

    Optional<SubjectResponseDto> get(UUID id);

    void delete(UUID id);

    SubjectResponseDto create(SubjectCreateDto dto);

    SubjectResponseDto update(SubjectUpdateDto dto);
}
