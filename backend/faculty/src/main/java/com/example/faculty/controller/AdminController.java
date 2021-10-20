package com.example.faculty.controller;

import com.example.faculty.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping(name = "/subject/create")
    public void createSubjectForm() {
    }

    @PostMapping(name = "/subject/create")
    public void doCreateSubject() {
    }

    @GetMapping(name = "/subject/{id}/update")
    public void editSubjectForm() {
    }

    @PatchMapping(name = "/subject/{id}/update")
    public void doUpdateSubject() {
    }

    @DeleteMapping(name = "/subject/{id}/delete")
    public void deleteSubject() {
    }

    @GetMapping(name = "/event/create")
    public void createEventForm() {
    }

    @PostMapping(name = "/event/create")
    public void createEvent() {
    }

    @GetMapping(name = "/event/{id}/update")
    public void updateEventForm() {
    }

    @PatchMapping(name = "/event/{id}/update")
    public void updateEvent() {
    }

    @DeleteMapping(name = "/event/{id}/delete")
    public void deleteEvent() {
    }

    @GetMapping("/tutor/{id}/update")
    public void updateTutorForm() {
    }

    @PatchMapping("/tutor/{id}/update")
    public void updateTutor() {
    }

    @GetMapping("/tutor/{id}/events")
    public void tutorEvents() {
    }

    @PostMapping(name = "/tutor/{tutorId}/events/{eventId}/add")
    public void addEventToTutor() {
    }

    @DeleteMapping(name = "/tutor/{tutorId}/events/{eventId}/delete")
    public void deleteEventToTutor() {
    }

    @GetMapping(name = "/requests")
    public void showAllRequestsList() {
    }

    @PostMapping(name = "/requests/{id}/confirm")
    public void confirmRequest() {
    }

    @PostMapping(name = "/requests/{id}/cancel")
    public void cancelRequest() {
    }

}
