package com.example.faculty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
public class AuthController {


    @GetMapping("/signIn")
    public String singIn(Model model) {
        return "signIn";
    }

    @PostMapping("/signIn")
    public String auth(Model model,
                       @RequestParam(value = "email", defaultValue = "") String email,
                       @RequestParam(value = "password", defaultValue = "") String password) {
        // todo write auth by email
        System.out.println("Email = " + email);
        System.out.println("Email = " + password);
        return "redirect:/signIn";
    }

    @GetMapping("/register")
    public String register() {

        return "registerStudent";
    }

    @PostMapping("/register")
    public String registerAuth(Model model,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("parental") String parental,
                               @RequestParam("email") String email,
                               @RequestParam("about") String about,
                               @RequestParam("course") Integer course,
                               @RequestParam("faculty") String faculty,
                               @RequestParam("password") String password) {
        System.out.println("");
        return "redirect:/";
    }


}
