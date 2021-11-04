package com.example.faculty.database.repository;

import com.example.faculty.database.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {

    @Query("select e from Event e")
    List<Event> getAllEvents();

}
