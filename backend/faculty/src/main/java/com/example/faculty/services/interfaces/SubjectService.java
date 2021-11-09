package com.example.faculty.services.interfaces;

import com.example.faculty.database.entity.Subject;
import com.example.faculty.database.enums.UserRole;
import com.example.faculty.models.requests.SubjectRequest;
import com.example.faculty.models.requests.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService {

    Subject createSubject(Subject subject);

    Subject updateSubject(Long userId, SubjectRequest SubjectRequest);

    Subject getSubjectById(Long subjectId);

    void deleteSubject(Long subjectId);

    List<Subject> getAll();

    List<Subject> getAllSubjectsByUser(Long userId);

    Page<Subject> getAllSubjectsByUser(Long userId, Pageable pageable);

    List<Subject> getAllSubjectsByFaculty(String faculty);

    Page<Subject> getAllSubjectsByFaculty(String faculty, Pageable pageable);

    List<Subject> getAllSubjectsByCourse(String course);

    Page<Subject> getAllSubjectsByCourse(String course, Pageable pageable);

    List<Subject> getAllSubjectsByTrim(String trim);

    Page<Subject> getAllSubjectsByTrim(String trim, Pageable pageable);
}
