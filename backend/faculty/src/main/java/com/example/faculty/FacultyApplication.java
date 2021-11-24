package com.example.faculty;

import com.example.faculty.services.implementations.EventServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class FacultyApplication  {



    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(FacultyApplication.class, args);
        test(applicationContext);
    }


    private static void test(ApplicationContext applicationContext) {
        EventServiceImpl eventService = applicationContext.getBean(EventServiceImpl.class);
        System.out.println(eventService.findEventForUserByYearAndMonth(2021,11));


    }

}
