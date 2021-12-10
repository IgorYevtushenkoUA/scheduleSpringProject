package com.example.faculty.database.repository;

import com.example.faculty.database.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;


public interface RequestRepository extends JpaRepository<Request, UUID> {

    @Query("delete from Request r where r.event.id=:eventUUID")
    void deleteByEvent(@Param("eventUUID") UUID eventUUID);

}
