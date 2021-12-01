package com.example.faculty.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class MainController {

    /*
    POST	/api/auth/signup	    signup new account
    POST	/api/auth/signin	    login an account
    GET	    /api/test/all	        retrieve public content
    GET	    /api/test/student	    access Student’s content
    GET	    /api/test/teacher	    access Teacher’s content
    GET	    /api/test/admin	access  Admin’s content
    */

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER') or hasRole('ADMINISTRATOR')")
    public String studentAccess() {
        return "Student Content.";
    }

    @GetMapping("/teacher")
    @PreAuthorize("hasRole('TEACHER')")
    public String teacherAccess() {
        return "Teacher Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String adminAccess() {
        return "Administrator Board.";
    }
}