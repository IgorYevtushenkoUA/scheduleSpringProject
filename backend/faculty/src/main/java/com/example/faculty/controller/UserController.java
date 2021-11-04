package com.example.faculty.controller;

import com.example.faculty.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping(value = "/subjects")
    public String showAllSubjects() {
        return "";
    }

    @GetMapping(value = "/events")
    public void showAllEvents() {

    }
}
