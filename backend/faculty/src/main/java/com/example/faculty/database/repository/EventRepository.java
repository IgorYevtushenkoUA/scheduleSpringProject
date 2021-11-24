package com.example.faculty.database.repository;

import com.example.faculty.database.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query(value = "SELECT e.id , e.created_at, e.updated_at, e.auditory, e.group_name, e.name, e.datetime, e.subject_id, e.user_id from Event e \n" +
            "inner join attendee a on a.event_id = e.id \n" +
            "where YEAR(e.datetime) = :year and  MONTH(e.datetime) = :month and DAY(e.datetime)= :day ", nativeQuery = true)
    List<Event> findEventForUserByYearAndMonthAndDay(@Param("year") int year,  @Param("month") int month, @Param("day") int day);

    @Query(value = "SELECT e.id , e.created_at, e.updated_at, e.auditory, e.group_name, e.name, e.datetime, e.subject_id, e.user_id from Event e \n" +
            "inner join attendee a on a.event_id = e.id \n" +
            "where YEAR(e.datetime) = :year and  MONTH(e.datetime) = :month ", nativeQuery = true)
    List<Event> findEventForUserByYearAndMonth(@Param("year") int year,  @Param("month") int month);


}
