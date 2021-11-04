package com.example.faculty.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tutor")
public class TutorController {

    @GetMapping(value = "/schedule")
    public void showTutorDefaultSchedule() {
    }

    @GetMapping(value = "/schedule/week/{num}")
    public void showTutorScheduleForWeekN() {
    }

    @GetMapping(value = "/event/{id}/update")
    public void eventUpdateForm() {
    }

    @PostMapping(value = "/event/{id}/update")
    public void createRequestToUpdateEvent() {
    }

}
