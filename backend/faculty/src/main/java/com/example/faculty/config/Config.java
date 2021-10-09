package com.example.faculty.config;

import com.example.faculty.services.interfaces.RequestService;
import com.example.faculty.services.implementations.RequestServiceImpl;
import com.example.faculty.services.interfaces.SubjectService;
import com.example.faculty.services.implementations.SubjectServiceImpl;
import com.example.faculty.services.interfaces.UserService;
import com.example.faculty.services.implementations.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.faculty")
public class Config {
    @Bean
    public RequestService requestService() {
        return new RequestServiceImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public SubjectService subjectService() {
        return new SubjectServiceImpl();
    }
}
