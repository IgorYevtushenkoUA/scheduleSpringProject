package com.example.faculty.services.implementations;

import com.example.faculty.database.dto.event.EventCreateDto;
import com.example.faculty.database.dto.event.EventResponseDto;
import com.example.faculty.database.dto.event.EventUpdateDto;
import com.example.faculty.database.entity.Event;
import com.example.faculty.database.mapstruct.mappers.IEventMapper;
import com.example.faculty.database.repository.EventRepository;
import com.example.faculty.services.interfaces.IEventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements IEventService {
    private final EventRepository repository;

    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EventResponseDto> getAll() {
        return repository.findAll().stream().map(IEventMapper.MAPPER::eventToResponse).collect(Collectors.toList());
    }

    @Override
    public Optional<EventResponseDto> get(UUID id) {
        return repository.findById(id).map(IEventMapper.MAPPER::eventToResponse);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public EventResponseDto create(EventCreateDto dto) {
        Event event = IEventMapper.MAPPER.createToEvent(dto);
        return IEventMapper.MAPPER.eventToResponse(repository.save(event));
    }

    @Override
    public EventResponseDto update(EventUpdateDto dto) {
        Event event = IEventMapper.MAPPER.updateToEvent(dto);
        return IEventMapper.MAPPER.eventToResponse(repository.save(event));
    }

    @Override
    public List<Event> findEventForUserByYearAndMonthAndDay(UUID userUUID, int year, int month, int day) {
        return repository.findEventForUserByYearAndMonthAndDay(userUUID.toString(), year, month, day);
    }

    @Override
    public List<Event> findEventForTeacherByYearAndMonthAndDay(UUID userUUID, int year, int month, int day) {
        return repository.findEventForTeacherByYearAndMonthAndDay(userUUID.toString(), year, month, day);
    }

    @Override
    public List<Event> findEventByYearAndMonthAndDay(int year, int month, int day) {
        return repository.findEventByYearAndMonthAndDay(year, month, day);
    }

    @Override
    public List<Event> findEventForUserByYearAndMonth(int year, int month) {
        return repository.findEventForUserByYearAndMonth(year, month);
    }

    @Override
    public List<Event> findAllBySubject(UUID subjectId) {
        return repository.findAllBySubject(subjectId);
    }

    @Override
    public List<Event> findByYearAndMonthAndDayAndSpeciality(int year, int month, int day, List<String> findBySpeciality) {
        return repository.findByYearAndMonthAndDayAndSpeciality(year, month, day, findBySpeciality);
    }

    @Override
    public List<Event> findByYearAndMonthAndDayAndCourse(int year,
                                                         int month,
                                                         int day,
                                                         List<Integer> course) {
        return repository.findByYearAndMonthAndDayAndCourse(year, month, day, course);
    }

    @Override
    public List<Event> findByYearAndMonthAndDayAndSpecialityAndCourse(int year,
                                                                      int month,
                                                                      int day,
                                                                      List<String> speciality,
                                                                      List<Integer> course) {
        return repository.findByYearAndMonthAndDayAndSpecialityAndCourse(year, month, day, speciality, course);
    }

    @Override
    public List<Event> findAllBySubjectAndGroup(UUID id, String group) {
        return repository.findAllBySubjectAndGroup(id, group);
    }

    @Override
    public List<Event> findAllStudentEventsBySubject(UUID userUUID, UUID subjectUUID) {
        return repository.findAllStudentEventsBySubject(userUUID, subjectUUID);
    }

}
