package com.example.faculty.controller;

import com.example.faculty.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/subject/create")
    public void createSubjectForm() {
    }

    @PostMapping(value = "/subject/create")
    public void doCreateSubject() {
    }

    @GetMapping(value = "/subject/{id}/update")
    public void editSubjectForm() {
    }

    @PatchMapping(value = "/subject/{id}/update")
    public void doUpdateSubject() {
    }

    @DeleteMapping(value = "/subject/{id}/delete")
    public void deleteSubject() {
    }

    @GetMapping(value = "/event/create")
    public void createEventForm() {
    }

    @PostMapping(value = "/event/create")
    public void createEvent() {
    }

    @GetMapping(value = "/event/{id}/update")
    public void updateEventForm() {
    }

    @PatchMapping(value = "/event/{id}/update")
    public void updateEvent() {
    }

    @DeleteMapping(value = "/event/{id}/delete")
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

    @PostMapping(value = "/tutor/{tutorId}/events/{eventId}/add")
    public void addEventToTutor() {
    }

    @DeleteMapping(value = "/tutor/{tutorId}/events/{eventId}/delete")
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
