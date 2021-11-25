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
    public String auth(Model model, @RequestParam(value = "email",defaultValue = "") String email) {
        // todo write auth by email
        System.out.println("Email = " + email);
        return "redirect:/signIn";
    }

}
