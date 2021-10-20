package com.example.faculty.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @PostMapping(name = "/event/{id}/enroll")
    public void enrollToEvent() {
    }

    @DeleteMapping(name = "/event/{id}/drop_out")
    public void dropOutFromEvent() {
    }

    @GetMapping(name = "/schedule")
    public void showDefaultSchedule() {
    }

    @GetMapping(name = "/schedule/week/{num}")
    public void showScheduleForWeekN() {
    }

}
