package com.example.faculty.config;

import com.example.faculty.request.RequestService;
import com.example.faculty.request.RequestServiceImpl;
import com.example.faculty.subject.SubjectService;
import com.example.faculty.subject.SubjectServiceImpl;
import com.example.faculty.user.UserService;
import com.example.faculty.user.UserServiceImpl;
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
