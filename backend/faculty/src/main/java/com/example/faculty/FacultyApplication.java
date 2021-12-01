package com.example.faculty;

import com.example.faculty.services.implementations.EventServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@SpringBootApplication
@EnableAspectJAutoProxy
public class FacultyApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = SpringApplication.run(FacultyApplication.class, args);

        File file = ResourceUtils.getFile("classpath:static/public/test.txt");
        if(file.exists()) {
            byte[] fileData = Files.readAllBytes(file.toPath());
            String fileContent = new String(fileData);
            System.out.println(fileContent);
        }

    }


    private static void test(ApplicationContext applicationContext) {
        EventServiceImpl eventService = applicationContext.getBean(EventServiceImpl.class);
        System.out.println(eventService.findEventForUserByYearAndMonth(2021, 11));
    }


}