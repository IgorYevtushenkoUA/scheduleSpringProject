package com.example.faculty.database.repository;

import com.example.faculty.database.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {

    Boolean existsSubjectByName(String name);

    List<Subject> findAllByFacultyOrderByCreationDateDesc(String faculty);

    Page<Subject> findAllByFacultyOrderByCreationDateDesc(String faculty, Pageable pageable);

}
