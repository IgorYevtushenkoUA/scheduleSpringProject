package com.example.faculty.database.repository;

import com.example.faculty.database.entity.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequestRepository extends CrudRepository<Request, UUID> {
}
