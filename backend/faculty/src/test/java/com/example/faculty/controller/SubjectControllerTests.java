package com.example.faculty.controller;


import com.example.faculty.database.dto.subject.SubjectCreateDto;
import com.example.faculty.database.dto.subject.SubjectResponseDto;
import com.example.faculty.database.entity.Subject;
import com.example.faculty.database.mapstruct.mappers.ISubjectMapper;
import com.example.faculty.database.repository.SubjectRepository;
import com.example.faculty.services.interfaces.ISubjectService;
import com.example.faculty.services.interfaces.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SubjectController.class)
@AutoConfigureMockMvc
public class SubjectControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ISubjectService subjectService;

    @MockBean
    private IUserService userService;


    @Test
    @WithMockUser(username = "admin",password = "password",authorities = "ADMINISTRATOR")
    public void whenPostWithEmptyBody_thenReturnStatus400()
            throws Exception {

        SubjectCreateDto u = CreateSubject("testSubject");
        mockMvc.perform(post("/api/subject/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString("")))
                .andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser(username = "admin",password = "password",authorities = "ADMINISTRATOR")
    public void whenInvalidPath_thenReturnStatus404() throws Exception {
        mockMvc.perform(get("/badurl"))
                .andExpect(status().isNotFound());
    }
    @Test
    @WithMockUser(username = "admin",password = "password",authorities = "ADMINISTRATOR")
    public void whenDeleteWithBadId_thenReturnStatus400() throws Exception {
        mockMvc.perform(delete("/api/subject/delete/-323")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }



    private SubjectCreateDto CreateSubject(String name){
        return new SubjectCreateDto(name,"faculty","speciality",3,3,"trim");
    }

}