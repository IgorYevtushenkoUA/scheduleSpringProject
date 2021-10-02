package com.example.faculty.config;

import com.example.faculty.request.RequestService;
import com.example.faculty.subject.SubjectService;
import com.example.faculty.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.faculty")
public class Config {
    @Bean
    public RequestService requestService() {
        return new RequestService();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public SubjectService subjectService() {
        return new SubjectService();
    }
}
