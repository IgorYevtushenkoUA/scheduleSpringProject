package com.example.faculty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping(value = "/subjects/create")
    public String createSubjectForm() {
        return "";
    }

    @PostMapping(value = "/subjects/create")
    public String doCreateSubject() {
        return "redirect:/";
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
    public void createEvent() {
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
