package com.example.faculty.database.repository;

import com.example.faculty.database.entity.Attendee;
import com.example.faculty.database.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
}
