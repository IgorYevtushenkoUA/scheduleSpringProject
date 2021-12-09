package com.example.faculty.database.repository;


import com.example.faculty.database.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AttendeeRepository extends JpaRepository<Attendee, UUID> {

    @Query("delete from Attendee a where a.user.id=:userUUID and a.event.id=:eventUUID")
    Attendee deleteByUserAndEvent(@Param("userUUID") UUID userUUID, @Param("eventUUID") UUID eventUUID);

}
