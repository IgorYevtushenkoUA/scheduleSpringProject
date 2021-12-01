package com.example.faculty;

import com.example.faculty.services.implementations.EventServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
//@EntityScan("com.example.faculty.database.entity")
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
