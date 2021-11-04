package com.example.faculty.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ProfileController {

    @Value("${app.message}")
    private String welcomeMessage;

    @GetMapping("/welcome")
    public String getDataBaseConnectionDetails() {
        return welcomeMessage;
    }

    @GetMapping("/signIn")
    public String signIn(){
        return "signIn.html";
    }

}
