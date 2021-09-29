package com.example.faculty.database.repository;

import com.example.faculty.database.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, UUID> {
}
