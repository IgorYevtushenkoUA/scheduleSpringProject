package com.example.faculty.services.implementations;

import com.example.faculty.database.entity.Subject;
import com.example.faculty.database.repository.SubjectRepository;
import com.example.faculty.services.implementations.base.BaseServiceImpl;
import com.example.faculty.services.interfaces.ISubjectService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubjectServiceImpl extends BaseServiceImpl<Subject, UUID> implements ISubjectService {
    private final SubjectRepository repository;

    public SubjectServiceImpl(SubjectRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
