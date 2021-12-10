package com.example.faculty.security;

import com.example.faculty.config.security.JwtUtils;
import com.example.faculty.config.security.WebSecurityConfig;
import com.example.faculty.database.dto.user.LoginRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsService userDetailsService;


    @Test
    public void whenUnauthorized_thenCreateSubjectReturn401()
            throws Exception {
        mockMvc.perform(get("/api/user/subjects/create"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
   
    @Test
    @WithMockUser(username = "teacher",password = "password",authorities = "TEACHER")
    public void whenTeacher_thenCreateSubjectReturn403()
            throws Exception {
        mockMvc.perform(get("/api/user/subjects/create"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(username = "teacher",password = "password",authorities = "TEACHER")
    public void whenTeacher_thenRequestsReturn403()
            throws Exception {
        mockMvc.perform(get("/api/user/requests"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "student",password = "password",authorities = "STUDENT")
    public void whenStudent_thenRequestsReturn403()
            throws Exception {
        mockMvc.perform(get("/api/user/requests"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(username = "student",password = "password",authorities = "STUDENT")
    public void whenAdmin_thenEventsReturn200()
            throws Exception {
        mockMvc.perform(get("/api/event"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void whenUnathorized_thenUserInfoReturn401()
            throws Exception {
        mockMvc.perform(get("/api/user/"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}