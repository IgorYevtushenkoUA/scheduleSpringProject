package com.example.faculty.database.repository;

import com.example.faculty.database.entity.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
