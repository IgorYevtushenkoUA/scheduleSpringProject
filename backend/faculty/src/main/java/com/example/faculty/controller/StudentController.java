package com.example.faculty.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @PostMapping(value = "/event/{id}/enroll")
    public void enrollToEvent() {
    }

    @DeleteMapping(value = "/event/{id}/leave")
    public void dropOutFromEvent() {
    }

    @GetMapping(value = "/schedule")
    public void showDefaultSchedule() {

    }

    @GetMapping(value = "/schedule/week/{num}")
    public void showScheduleForWeekN() {
    }

}
