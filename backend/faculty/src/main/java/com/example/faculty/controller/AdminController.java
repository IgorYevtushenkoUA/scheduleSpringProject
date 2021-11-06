package com.example.faculty.controller;

import com.example.faculty.database.dto.CreateEventDto;
import com.example.faculty.database.dto.CreateSubjectDto;
import com.example.faculty.util.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping(value = "/subjects/create")
    public String createSubjectForm() throws BadRequestException {
        if(1==1) throw new BadRequestException("Bad_request_lol");
        return "";
    }

    @PostMapping(value = "/subjects/create")
    public ResponseEntity<String> doCreateSubject(@Valid @Validated @RequestBody  CreateSubjectDto subjectDto) throws BadRequestException {
        System.out.println(subjectDto);
        return ResponseEntity.ok("Subject is created successfully");
    }

    @GetMapping(value = "/subjects/{id}/update")
    public String editSubjectForm() {
        return "";
    }

    @PatchMapping(value = "/subjects/{id}/update")
    public String doUpdateSubject() {
        return "";
    }

    @DeleteMapping(value = "/subjects/{id}/delete")
    public void deleteSubject() {
    }

    @GetMapping(value = "/events/create")
    public void createEventForm() {
    }

    @PostMapping(value = "/events/create")
    public ResponseEntity<String> createEvent(@Valid @RequestBody CreateEventDto eventDto) {

        return ResponseEntity.ok("Event is created successfully");
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
