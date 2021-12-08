package com.example.faculty;

import com.example.faculty.database.dto.subject.SubjectResponseDto;
import com.example.faculty.services.implementations.EventServiceImpl;
import com.example.faculty.services.implementations.RequestServiceImpl;
import com.example.faculty.services.implementations.SubjectServiceImpl;
import com.example.faculty.services.implementations.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@SpringBootApplication
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