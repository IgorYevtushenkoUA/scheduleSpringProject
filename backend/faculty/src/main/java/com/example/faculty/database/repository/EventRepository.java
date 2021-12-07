package com.example.faculty.database.repository;

import com.example.faculty.database.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    // todo add UUID
    @Query(value = "SELECT e.id , e.created_at, e.updated_at, e.auditory, e.group_name, e.name, e.datetime, e.subject_id, e.user_id from Event e \n" +
            "inner join attendee a on a.event_id = e.id \n" +
            "where YEAR(e.datetime) = :year and  MONTH(e.datetime) = :month and DAY(e.datetime)= :day ", nativeQuery = true)
    List<Event> findEventForUserByYearAndMonthAndDay(@Param("year") int year, @Param("month") int month, @Param("day") int day);

    @Query(value = "SELECT e.id , e.created_at, e.updated_at, e.auditory, e.group_name, e.name, e.datetime, e.subject_id, e.user_id from Event e \n" +
            "where YEAR(e.datetime) = :year and  MONTH(e.datetime) = :month and DAY(e.datetime)= :day ", nativeQuery = true)
    List<Event> findEventByYearAndMonthAndDay(@Param("year") int year, @Param("month") int month, @Param("day") int day);


    @Query(value = "SELECT e.id , e.created_at, e.updated_at, e.auditory, e.group_name, e.name, e.datetime, e.subject_id, e.user_id from Event e \n" +
            "inner join attendee a on a.event_id = e.id \n" +
            "where YEAR(e.datetime) = :year and  MONTH(e.datetime) = :month ", nativeQuery = true)
    List<Event> findEventForUserByYearAndMonth(@Param("year") int year, @Param("month") int month);


    @Query("select e from Event e " +
            "inner join Subject s on s.id=e.subject.id " +
            "where s.id= :subjectId")
    List<Event> findAllBySubject(@Param("subjectId") UUID subjectId);

    @Query(value = "SELECT e.id , e.created_at, e.updated_at, e.auditory, e.group_name, e.name, e.datetime, e.subject_id, e.user_id from Event e \n" +
            "inner join subject s on s.id = e.subject_id \n" +
            "where YEAR(e.datetime) = :year and  MONTH(e.datetime) = :month and DAY(e.datetime)= :day " +
            "and s.speciality in (:speciality)", nativeQuery = true)
    List<Event> findByYearAndMonthAndDayAndSpeciality(@Param("year") int year,
                                 @Param("month") int month,
                                 @Param("day") int day,
                                 @Param("speciality") List<String> speciality);

    @Query(value = "SELECT e.id , e.created_at, e.updated_at, e.auditory, e.group_name, e.name, e.datetime, e.subject_id, e.user_id from Event e \n" +
            "inner join subject s on s.id = e.subject_id \n" +
            "where YEAR(e.datetime) = :year and  MONTH(e.datetime) = :month and DAY(e.datetime)= :day " +
            "and s.course in (:course)", nativeQuery = true)
    List<Event> findByYearAndMonthAndDayAndCourse(@Param("year") int year,
                             @Param("month") int month,
                             @Param("day") int day,
                             @Param("course") List<Integer> course);

    @Query(value = "SELECT e.id , e.created_at, e.updated_at, e.auditory, e.group_name, e.name, e.datetime, e.subject_id, e.user_id from Event e \n" +
            "inner join subject s on s.id = e.subject_id \n" +
            "where YEAR(e.datetime) = :year and  MONTH(e.datetime) = :month and DAY(e.datetime)= :day " +
            "and s.speciality in (:speciality) and s.course in (:course)", nativeQuery = true)
    List<Event> findByYearAndMonthAndDayAndSpecialityAndCourse(@Param("year") int year,
                                          @Param("month") int month,
                                          @Param("day") int day,
                                          @Param("speciality") List<String> speciality,
                                          @Param("course") List<Integer> course);

}
