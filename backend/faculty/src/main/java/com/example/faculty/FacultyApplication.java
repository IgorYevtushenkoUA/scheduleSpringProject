package com.example.faculty;

import com.example.faculty.services.implementations.EventServiceImpl;
import com.example.faculty.services.implementations.RequestServiceImpl;
import com.example.faculty.services.implementations.SubjectServiceImpl;
import com.example.faculty.services.implementations.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.IOException;

@SpringBootApplication
@EnableAspectJAutoProxy
public class FacultyApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = SpringApplication.run(FacultyApplication.class, args);

        test(applicationContext);

    }

    private static void test(ApplicationContext applicationContext) {
        EventServiceImpl eventService = applicationContext.getBean(EventServiceImpl.class);
        SubjectServiceImpl subjectService = applicationContext.getBean(SubjectServiceImpl.class);
        RequestServiceImpl requestService = applicationContext.getBean(RequestServiceImpl.class);
        UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);

        System.out.println(requestService.getAll());
        System.out.println(eventService.getAll());
    }

}