package com.example.faculty.database.mapstruct.mappers;

import com.example.faculty.database.dto.subject.SubjectCreateDto;
import com.example.faculty.database.dto.subject.SubjectResponseDto;
import com.example.faculty.database.dto.subject.SubjectShortDto;
import com.example.faculty.database.dto.subject.SubjectUpdateDto;
import com.example.faculty.database.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ISubjectMapper {
    ISubjectMapper MAPPER = Mappers.getMapper(ISubjectMapper.class);

    SubjectResponseDto subjectToResponse(Subject subject);

    SubjectShortDto subjectToShortDto(Subject subject);

    Subject createToSubject(SubjectCreateDto dto);

    Subject updateToSubject(SubjectUpdateDto dto);
}
