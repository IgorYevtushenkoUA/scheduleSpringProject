package com.example.faculty;

import com.example.faculty.config.AppProperties;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class FacultyApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(FacultyApplication.class, args);
    }
}
