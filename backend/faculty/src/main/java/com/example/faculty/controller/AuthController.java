package com.example.faculty.controller;

import com.example.faculty.config.security.JwtUtils;
import com.example.faculty.database.dto.user.JwtDto;
import com.example.faculty.database.dto.user.LoginDto;
import com.example.faculty.database.entity.User;
import com.example.faculty.database.enums.UserRole;
import com.example.faculty.database.repository.UserRepository;
import com.example.faculty.database.dto.user.SignupRequest;
import com.example.faculty.services.interfaces.IUserService;
import com.example.faculty.util.annotations.LogInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
@LogInfo
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final IUserService service;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, IUserService service, PasswordEncoder encoder, JwtUtils jwtUtils, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
    }

    @ModelAttribute("signUpRequest")
    public SignupRequest userDto() {
        return new SignupRequest();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registerStudent";
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "signIn";
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();
        return ResponseEntity.ok(
                new JwtDto(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        userDetails.getRole()));
    }

    @GetMapping(value = "/registration")
    public ModelAndView displayRegistration(ModelAndView modelAndView, SignupRequest signUpRequest) {
        modelAndView.addObject("user", signUpRequest);
        modelAndView.setViewName("registerStudent");
        return modelAndView;
    }

    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("signUpRequest") SignupRequest signUpRequest,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registerStudent";
        } else {
            User existingUser = service.findByEmail(signUpRequest.getEmail());
            if (existingUser != null) {
                model.addAttribute("message", true);
            } else {

                if (signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword()))
                    model.addAttribute("message_confirm", true);

                // Create new user's account
                User user = new User(signUpRequest.getName(),
                        signUpRequest.getSurname(),
                        signUpRequest.getParental(),
                        signUpRequest.getEmail(),
                        encoder.encode(signUpRequest.getPassword()),
                        signUpRequest.getEmail(),
                        signUpRequest.getAbout(),
                        signUpRequest.getCourse(),
                        signUpRequest.getFaculty(),
                        UserRole.STUDENT);

                userRepository.save(user);

                model.addAttribute("success", true);
            }
        }

        return "registerStudent";
    }

}
