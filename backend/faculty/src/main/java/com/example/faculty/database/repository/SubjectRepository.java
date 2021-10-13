package com.example.faculty.database.repository;

import com.example.faculty.database.entity.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
