package com.example.faculty.controller;

import com.example.faculty.database.dto.subject.SubjectCreateDto;
import com.example.faculty.database.repository.SubjectRepository;
import com.example.faculty.services.interfaces.ISubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SubjectController.class)
public class SubjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ISubjectService subjectService;

    @MockBean
    private SubjectRepository subjectRepository;

    @Test
    public void whenPostSubject_thenReturnStatus200()
            throws Exception {

        SubjectCreateDto u = CreateSubject("testSubject");
        mockMvc.perform(post("/api/subject/create")
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isOk());
    }
    @Test
    public void whenPostWithEmptyBody_thenReturnStatus400()
            throws Exception {

        SubjectCreateDto u = CreateSubject("testSubject");
        mockMvc.perform(post("/api/subject/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString("")))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void whenInvalidPath_thenReturnStatus404() throws Exception {
        mockMvc.perform(get("/badurl"))
                .andExpect(status().isNotFound());
    }
    @Test
    public void whenDeleteWithBadId_thenReturnStatus400() throws Exception {
        mockMvc.perform(delete("/api/subject/delete/-323")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }


    private SubjectCreateDto CreateSubject(String name){
        return new SubjectCreateDto(name,"faculty","speciality",3,3,"trim");
    }

}
