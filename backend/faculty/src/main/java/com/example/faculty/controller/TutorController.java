package com.example.faculty.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tutor")
public class TutorController {

    @GetMapping(name = "/schedule")
    public void showTutorDefaultSchedule() {
    }

//    @GetMapping(name = "/schedule/week/{num}")
//    public void showTutorScheduleForWeekN() {
//    }
//
//    @GetMapping(name = "/event/{id}/update")
//    public void eventUpdateForm() {
//    }
//
//    @PostMapping(name = "/event/{id}/update")
//    public void createRequestToUpdateEvent() {
//    }

}
