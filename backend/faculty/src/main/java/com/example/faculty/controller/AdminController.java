package com.example.faculty.controller;

import com.example.faculty.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping(name = "/subjects")
    public void showAllSubjects() {

    }

//    @GetMapping(name = "/subjects/create")
//    public void createSubjectForm() {
//    }

//    @PostMapping(name = "/subjects/create")
//    public void doCreateSubject() {
//    }
//
//    @GetMapping(name = "/subjects/{id}/update")
//    public void editSubjectForm() {
//    }
//
//    @PatchMapping(name = "/subjects/{id}/update")
//    public void doUpdateSubject() {
//    }
//
//    @DeleteMapping(name = "/subjects/{id}/delete")
//    public void deleteSubject() {
//    }
//
//    @GetMapping(name = "/events")
//    public void showAllEvents() {
//
//    }
//
//    @GetMapping(name = "/events/create")
//    public void createEventForm() {
//    }
//
//    @PostMapping(name = "/events/create")
//    public void createEvent() {
//    }
//
//    @GetMapping(name = "/events/{id}/update")
//    public void updateEventForm() {
//    }
//
//    @PatchMapping(name = "/events/{id}/update")
//    public void updateEvent() {
//    }
//
//    @DeleteMapping(name = "/events/{id}/delete")
//    public void deleteEvent() {
//    }
//
//
//    @GetMapping(name = "/tutors")
//    public void showAllTutors() {
//
//    }
//
//    @GetMapping("/tutors/{id}/update")
//    public void updateTutorForm() {
//    }
//
//    @PatchMapping("/tutors/{id}/update")
//    public void updateTutor() {
//    }
//
//    @GetMapping("/tutors/{id}/events")
//    public void tutorEvents() {
//    }
//
//    @PostMapping(name = "/tutors/{tutorId}/events/{eventId}/add")
//    public void addEventToTutor() {
//    }
//
//    @DeleteMapping(name = "/tutors/{tutorId}/events/{eventId}/delete")
//    public void deleteEventToTutor() {
//    }
//
//    @GetMapping(name = "/requests")
//    public void showAllRequestsList() {
//    }
//
//    @PostMapping(name = "/requests/{id}/confirm")
//    public void confirmRequest() {
//    }
//
//    @PostMapping(name = "/requests/{id}/cancel")
//    public void cancelRequest() {
//    }

}
