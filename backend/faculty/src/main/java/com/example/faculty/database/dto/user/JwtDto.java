package com.example.faculty.database.dto.user;

import com.example.faculty.database.enums.UserRole;
import lombok.Data;

import java.util.UUID;

@Data
public class JwtDto {
    private String token;
    private String type = "Bearer";
    private UUID id;
    private String username;
    private String email;
    private UserRole role;

    public JwtDto(String accessToken, UUID id, String username, String email, UserRole role) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;

    }
}