package com.example.faculty.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tutor")
public class TutorController {

    @GetMapping(value = "/info")
    public void tutorInfoForm() {
    }

    @PostMapping(value = "/into")
    public void createTutorInfo() {
    }

    @GetMapping(value = "/schedule")
    public void showTutorDefaultSchedule() {
    }

    @GetMapping(value = "/schedule/week/{num}")
    public void showTutorScheduleForWeekN() {
    }

    @GetMapping(value = "event/{id}/update")
    public void eventUpdateForm() {
    }

    @PostMapping(value = "event/{id}/update")
    public void createRequestToUpdateEvent() {
    }

}
