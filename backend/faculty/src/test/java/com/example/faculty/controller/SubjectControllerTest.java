package com.example.faculty.controller;

import com.example.faculty.database.dto.subject.SubjectCreateDto;
import com.example.faculty.database.dto.subject.SubjectResponseDto;
import com.example.faculty.database.entity.Subject;
import com.example.faculty.database.mapstruct.mappers.ISubjectMapper;
import com.example.faculty.database.repository.SubjectRepository;
import com.example.faculty.services.interfaces.ISubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class SubjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ISubjectService subjectService;


    @Test
    public void whenPostSubject_thenReturnStatus200()
            throws Exception {
        SubjectCreateDto s = CreateSubject("testCreateSubject");
        Subject sub = ISubjectMapper.MAPPER.createToSubject(s);

        given(subjectService.create(Mockito.any())).willReturn(ISubjectMapper
                .MAPPER.subjectToResponse(sub));

        mockMvc.perform(post("/api/subject/create")
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(s)))
                .andDo(print())
                .andExpect(jsonPath("$.name", is(s.getName())))
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
    @Test
    public void whenGetAll_thenListOfSubjectsAndStatus200() throws Exception {
        SubjectCreateDto s1 = CreateSubject("testSubject1");
        SubjectCreateDto s2 = CreateSubject("testSubject2");
        Subject sub1 = ISubjectMapper.MAPPER.createToSubject(s1);
        Subject sub2 = ISubjectMapper.MAPPER.createToSubject(s2);
        SubjectResponseDto sr1 = ISubjectMapper.MAPPER.MAPPER.subjectToResponse(sub1);
        SubjectResponseDto sr2 = ISubjectMapper.MAPPER.MAPPER.subjectToResponse(sub2);
        List<SubjectResponseDto> allEmployees = Arrays.asList(sr1,sr2);

        given(subjectService.getAll()).willReturn(allEmployees);

        mockMvc.perform(get("/api/subject")
                        .contentType("application/json")).andDo(print()).andExpect(jsonPath("$[0].name", is(s1.getName())))
        .andExpect(jsonPath("$[1].name", is(s2.getName())))
                .andExpect(status().isOk());
    }




    private SubjectCreateDto CreateSubject(String name){
        return new SubjectCreateDto(name,"faculty","speciality",3,3,"trim");
    }

}
