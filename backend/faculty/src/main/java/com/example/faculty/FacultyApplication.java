package com.example.faculty;

import com.example.faculty.services.implementations.EventServiceImpl;
import logger.ConsoleLoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FacultyApplication {
    public static void main(String[] args) {
//        SpringApplication.run(FacultyApplication.class, args);
//        SpringApplication.run(FacultyApplication.class, args).getBean(ConsoleLoggerService.class).log("FacultyApplication","Launched successfully");
        Logger logger = LoggerFactory.getLogger(FacultyApplication.class);
        ApplicationContext applicationContext = SpringApplication.run(FacultyApplication.class, args);
        logger.info("It is working");
        test(applicationContext);
    }

    private static void test(ApplicationContext applicationContext){
        EventServiceImpl eventService = applicationContext.getBean(EventServiceImpl.class);
        System.out.println(eventService.getEventById(1L));
    }
}
