package com.example.faculty.services.interfaces;

import com.example.faculty.database.entity.Subject;
import com.example.faculty.models.requests.SubjectRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService {

    Subject createSubject(SubjectRequest subjectRequest);

    Subject updateSubject(Long subjectId, SubjectRequest subjectRequest);

    Subject getSubjectById(Long subjectId);

    void deleteSubject(Long subjectId);

    List<Subject> getAllSubjectsByFaculty(String faculty);

    Page<Subject> getAllSubjectsByFaculty(String faculty, Pageable pageable);
}
