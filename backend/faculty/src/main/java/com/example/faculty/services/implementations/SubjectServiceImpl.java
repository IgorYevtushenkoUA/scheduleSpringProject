package com.example.faculty.services.implementations;

import com.example.faculty.database.dto.subject.SubjectCreateDto;
import com.example.faculty.database.dto.subject.SubjectResponseDto;
import com.example.faculty.database.dto.subject.SubjectUpdateDto;
import com.example.faculty.database.entity.Subject;
import com.example.faculty.database.mapstruct.mappers.ISubjectMapper;
import com.example.faculty.database.repository.SubjectRepository;
import com.example.faculty.services.interfaces.ISubjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements ISubjectService {
    private final SubjectRepository repository;

    public SubjectServiceImpl(SubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SubjectResponseDto> getAll() {
        return repository.findAll().stream().map(ISubjectMapper.MAPPER::subjectToResponse).collect(Collectors.toList());
    }

    @Override
    public Optional<SubjectResponseDto> get(UUID id) {
        return repository.findById(id).map(ISubjectMapper.MAPPER::subjectToResponse);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public SubjectResponseDto create(SubjectCreateDto dto) {
        Subject subject = ISubjectMapper.MAPPER.createToSubject(dto);
        return ISubjectMapper.MAPPER.subjectToResponse(repository.save(subject));
    }

    @Override
    public SubjectResponseDto update(SubjectUpdateDto dto) {
        Subject subject = ISubjectMapper.MAPPER.updateToSubject(dto);
        return ISubjectMapper.MAPPER.subjectToResponse(repository.save(subject));
    }

    @Override
    public List<SubjectResponseDto> getByName(String name) {
        return repository.findByName(name).stream().map(ISubjectMapper.MAPPER::subjectToResponse).collect(Collectors.toList());
    }


}
