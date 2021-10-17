package com.example.faculty.database.repository;

import com.example.faculty.database.entity.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Long> {
}
