package com.example.faculty;

import com.example.faculty.services.implementations.EventServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FacultyApplication implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(FacultyApplication.class);


    @Autowired
    EventServiceImpl eventService;


    public static void main(String[] args) {
        SpringApplication.run(FacultyApplication.class, args);
        logger.info("snickers");
    }

    @Override
    public void run(String... args) {
        showAllEvents();
    }

    public void showAllEvents() {

    }

}
