package com.example.faculty.services.interfaces;

import com.example.faculty.database.dto.event.EventCreateDto;
import com.example.faculty.database.dto.event.EventResponseDto;
import com.example.faculty.database.dto.event.EventUpdateDto;
import com.example.faculty.database.entity.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEventService {
    List<EventResponseDto> getAll();

    Optional<EventResponseDto> get(UUID id);

    void delete(UUID id);

    EventResponseDto create(EventCreateDto dto);

    EventResponseDto update(EventUpdateDto dto);

    List<Event> findEventForUserByYearAndMonthAndDay(UUID userUUId, int year, int month, int day);

    List<Event> findEventForTeacherByYearAndMonthAndDay(UUID userUUID, int year, int month, int day);

    List<Event> findEventByYearAndMonthAndDay(int year, int month, int day);

    List<Event> findEventForUserByYearAndMonth(int year, int month);

    List<Event> findAllBySubject(UUID subjectId);

    List<Event> findByYearAndMonthAndDayAndSpeciality(int year, int month, int day, List<String> findBySpeciality);

    List<Event> findByYearAndMonthAndDayAndCourse(int year, int month, int day, List<Integer> course);

    List<Event> findByYearAndMonthAndDayAndSpecialityAndCourse(int year, int month, int day, List<String> speciality, List<Integer> course);

    List<Event> findAllBySubjectAndGroup(UUID id, String group);

    // todo add userUUID
    List<Event> findAllStudentEventsBySubject(UUID subjectUUID);
}
