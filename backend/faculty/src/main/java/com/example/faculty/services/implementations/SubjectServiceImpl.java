package com.example.faculty.services.implementations;

import com.example.faculty.database.entity.Subject;
import com.example.faculty.database.repository.SubjectRepository;
import com.example.faculty.models.requests.SubjectRequest;
import com.example.faculty.services.interfaces.SubjectService;
import com.example.faculty.util.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public Subject createSubject(SubjectRequest subjectRequest) {
        if (subjectRepository.existsSubjectByName(subjectRequest.getName()))
            throw new BadRequestException("This subject is already exists");

        return subjectRepository.save(Subject.builder()
                .name(subjectRequest.getName())
                .faculty(subjectRequest.getFaculty())
                .speciality(subjectRequest.getSpeciality())
                .course(subjectRequest.getCourse())
                .code(subjectRequest.getCode())
                .trim(subjectRequest.getTrim())
                .build());
    }

    @Override
    public Subject updateSubject(Long subjectId, SubjectRequest subjectRequest) {
        Subject subject = getSubjectById(subjectId);

        subject.setName(subjectRequest.getName());
        subject.setFaculty(subjectRequest.getFaculty());
        subject.setSpeciality(subjectRequest.getSpeciality());
        subject.setCourse(subjectRequest.getCourse());
        subject.setCode(subjectRequest.getCode());
        subject.setTrim(subjectRequest.getTrim());
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow(
                () -> new BadRequestException("Subject with id " + subjectId + " does not exist!"));
    }

    @Override
    public void deleteSubject(Long subjectId) {
        subjectRepository.delete(getSubjectById(subjectId));
    }

    @Override
    public List<Subject> getAllSubjectsByFaculty(String faculty) {
        return subjectRepository.findAllByFacultyOrderByCreationDateDesc(faculty);
    }

    @Override
    public Page<Subject> getAllSubjectsByFaculty(String faculty, Pageable pageable) {
        return subjectRepository.findAllByFacultyOrderByCreationDateDesc(faculty, pageable);
    }

}
