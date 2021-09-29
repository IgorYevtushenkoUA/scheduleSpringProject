package com.example.faculty.database.repository;

import com.example.faculty.database.entity.Atendee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AtendeeRepository extends CrudRepository<Atendee, UUID> {
}
