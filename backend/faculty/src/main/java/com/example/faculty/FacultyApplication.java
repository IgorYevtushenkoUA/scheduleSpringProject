package com.example.faculty;

import logger.ConsoleLoggerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FacultyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacultyApplication.class, args).getBean(ConsoleLoggerService.class).log("FacultyApplication","Launched successfully");
    }

}
