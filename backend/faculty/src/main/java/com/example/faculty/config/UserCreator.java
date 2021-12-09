package com.example.faculty.config;

import com.example.faculty.database.entity.User;
import com.example.faculty.database.enums.UserRole;
import com.example.faculty.database.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
class UserCreator {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserCreator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        userRepository.save(new User("test", "test@test.com", encoder.encode("password"), "Igor", "Igorok"));
    }
}
