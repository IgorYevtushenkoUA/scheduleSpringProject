package com.example.faculty.services.implementations;

import com.example.faculty.database.entity.Subject;
import com.example.faculty.database.repository.SubjectRepository;
import com.example.faculty.services.interfaces.ISubjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectServiceImpl implements ISubjectService {
    private final SubjectRepository repository;

    public SubjectServiceImpl(SubjectRepository repository) {
        this.repository = repository;
    }
}
