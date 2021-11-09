package com.example.faculty;

import com.example.faculty.database.entity.Event;
import com.example.faculty.services.implementations.EventServiceImpl;
import com.example.faculty.services.interfaces.EventService;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


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
