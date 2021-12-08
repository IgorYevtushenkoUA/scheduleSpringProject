package com.example.faculty.controller;

import com.example.faculty.config.security.JwtUtils;
import com.example.faculty.database.entity.Role;
import com.example.faculty.database.entity.User;
import com.example.faculty.database.entity.UserDetailsImpl;
import com.example.faculty.database.enums.UserRole;
import com.example.faculty.database.repository.RoleRepository;
import com.example.faculty.database.repository.UserRepository;
import com.example.faculty.database.request.LoginRequest;
import com.example.faculty.database.request.SignupRequest;
import com.example.faculty.database.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @GetMapping(value = "/registration")
    public ModelAndView displayRegistration(ModelAndView modelAndView, SignupRequest signUpRequest) {
        modelAndView.addObject("user", signUpRequest);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registerUser(@Valid @RequestBody SignupRequest signUpRequest,
                                     BindingResult result, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            User existingUser = userRepository.findByEmail(signUpRequest.getEmail());
            if (existingUser != null) {
                modelAndView.setViewName("message");
            } else {

                if(signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword()))
                    modelAndView.setViewName("message_confirm");

                Role studentRole = roleRepository.findRoleByName(UserRole.STUDENT)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                Set<Role> roles = new HashSet<>();
                roles.add(studentRole);

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
                        roles);

                userRepository.save(user);

                modelAndView.setViewName("success");
            }
        }

        return modelAndView;
    }


}

