package com.example.faculty.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @PostMapping(value = "/event/{id}/enroll")
    public void enrollToEvent() {
    }

    @DeleteMapping(value = "/event/{id}/drop_out")
    public void dropOutFromEvent() {
    }

    @GetMapping(value = "/schedule")
    public void showDefaultSchedule() {

    }

    @GetMapping(value = "/schedule/week/{num}")
    public void showScheduleForWeekN() {
    }

}
