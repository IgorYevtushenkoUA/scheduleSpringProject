package com.example.faculty.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@ConditionalOnProperty(prefix = "spring.profiles", name = "active",havingValue = "dev")
public class ProfilesConfig {
    @Value("${spring.profiles.active}")
    private String profile;
    @Value("${spring.datasource.url}")
    private String db;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            System.out.println("\nApplication is now running in "+profile.toUpperCase()+" mode");
            System.out.println("Current datasource: "+db);
        };
    }
}
