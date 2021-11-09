package com.example.faculty.services.implementations;

import com.example.faculty.database.entity.Subject;
import com.example.faculty.database.repository.SubjectRepository;
import com.example.faculty.models.requests.SubjectRequest;
import com.example.faculty.services.interfaces.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubject(Long userId, SubjectRequest SubjectRequest) {
        return null;
    }

    @Override
    public Subject getSubjectById(Long subjectId) {
        return null;
    }

    @Override
    public void deleteSubject(Long subjectId) {

    }

    @Override
    public List<Subject> getAll() {
        return (List<Subject>) subjectRepository.findAll();
    }

    @Override
    public List<Subject> getAllSubjectsByUser(Long userId) {
        return null;
    }

    @Override
    public Page<Subject> getAllSubjectsByUser(Long userId, Pageable pageable) {
        return null;
    }

    @Override
    public List<Subject> getAllSubjectsByFaculty(String faculty) {
        return null;
    }

    @Override
    public Page<Subject> getAllSubjectsByFaculty(String faculty, Pageable pageable) {
        return null;
    }

    @Override
    public List<Subject> getAllSubjectsByCourse(String course) {
        return null;
    }

    @Override
    public Page<Subject> getAllSubjectsByCourse(String course, Pageable pageable) {
        return null;
    }

    @Override
    public List<Subject> getAllSubjectsByTrim(String trim) {
        return null;
    }

    @Override
    public Page<Subject> getAllSubjectsByTrim(String trim, Pageable pageable) {
        return null;
    }
}
