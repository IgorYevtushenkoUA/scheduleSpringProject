package com.example.faculty.controller;

import com.example.faculty.database.entity.Event;
import com.example.faculty.database.entity.Subject;
import com.example.faculty.services.interfaces.EventService;
import com.example.faculty.services.interfaces.SubjectService;
import com.example.faculty.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    EventService eventService;

    @GetMapping(value = "/subjects")
    public List<Subject> showAllSubjects() {
        List<Subject> subjects = subjectService.getAll();
        return subjects;
    }

    @GetMapping(value = "/events")
    public List<Event> showAllEvents() {
        List<Event> events = eventService.getAll();
        return events;
    }
}
