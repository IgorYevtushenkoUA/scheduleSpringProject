package com.example.faculty.controller;

import com.example.faculty.database.dto.CreateEventDto;
import com.example.faculty.database.entity.Event;
import com.example.faculty.database.entity.Subject;
import com.example.faculty.services.implementations.SubjectServiceImpl;
import com.example.faculty.services.interfaces.EventService;
import com.example.faculty.services.interfaces.SubjectService;
import com.example.faculty.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    EventService eventService;

    @GetMapping(value = "/subjects/create")
    public String createSubjectForm() throws BadRequestException {
        return "";
    }

    @PostMapping(value = "/subjects/create")
    public ResponseEntity<String> doCreateSubject(@Valid @Validated @RequestBody Subject s) throws BadRequestException {
        Subject subject = Subject.builder()
                .name(s.getName())
                .faculty(s.getFaculty())
                .speciality(s.getSpeciality())
                .course(s.getCourse())
                .code(s.getCode())
                .trim(s.getTrim())
                .build();


        subject = subjectService.createSubject(subject);
        return ResponseEntity.ok("Subject is created successfully");
    }

    @GetMapping(value = "/subjects/{id}/update")
    public String editSubjectForm() {
        return "";
    }

    @PatchMapping(value = "/subjects/{id}/update")
    public String updateSubject() {
        return "";
    }

    @DeleteMapping(value = "/subjects/{id}/delete")
    public void deleteSubject() {
    }

    @GetMapping(value = "/events/create")
    public void createEventForm() {
    }

    @PostMapping(value = "/events/create")
    public ResponseEntity<String> createEvent(@Valid @RequestBody CreateEventDto c) {
        Event event = Event.builder()
                .organizer(c.getOrganizer())
                .subjectId(c.getSubjectId())
                .datetime(new Timestamp(System.currentTimeMillis()))
                .group(c.getGroup())
                .name(c.getName())
                .auditory(c.getAuditory())
                .isRequest(c.isRequest())
                .build();

        event = eventService.createEvent(event);
        System.out.println("Was created new event : " + event.getId());
        return ResponseEntity.ok("Event is created successfully : " + event);
    }

    @GetMapping(value = "/events/{id}/update")
    public void updateEventForm() {
    }

    @PatchMapping(value = "/events/{id}/update")
    public void updateEvent() {
    }

    @DeleteMapping(value = "/events/{id}/delete")
    public void deleteEvent() {
    }


    @GetMapping(value = "/tutors")
    public void showAllTutors() {

    }

    @GetMapping("/tutors/{id}/update")
    public void updateTutorForm() {
    }

    @PatchMapping("/tutors/{id}/update")
    public void updateTutor() {
    }

    @GetMapping("/tutors/{id}/events")
    public void tutorEvents() {
    }

    @PostMapping(value = "/tutors/{tutorId}/events/{eventId}/add")
    public void addEventToTutor() {
    }

    @DeleteMapping(value = "/tutors/{tutorId}/events/{eventId}/delete")
    public void deleteEventToTutor() {
    }

    @GetMapping(value = "/requests")
    public void showAllRequestsList() {
    }

    @PostMapping(value = "/requests/{id}/confirm")
    public void confirmRequest() {
    }

    @PostMapping(value = "/requests/{id}/cancel")
    public void cancelRequest() {
    }

}
