package com.example.faculty.controller;

import com.example.faculty.database.dto.event.EventCreateDto;
import com.example.faculty.database.dto.event.EventResponseDto;
import com.example.faculty.database.dto.subject.SubjectCreateDto;
import com.example.faculty.database.dto.subject.SubjectResponseDto;
import com.example.faculty.database.entity.Event;
import com.example.faculty.database.entity.Subject;
import com.example.faculty.database.mapstruct.mappers.IEventMapper;
import com.example.faculty.database.mapstruct.mappers.ISubjectMapper;
import com.example.faculty.services.interfaces.IEventService;
import com.example.faculty.services.interfaces.ISubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IEventService eventService;
    @Test
    @WithMockUser(username = "admin",password = "password",authorities = "ADMINISTRATOR")
    public void whenPostEvent_thenReturnStatus200()
            throws Exception {
        EventCreateDto s = new EventCreateDto(java.sql.Timestamp.valueOf("2021-09-23 10:10:10.0"), UUID.randomUUID(),UUID.randomUUID(),"group","name","auditory");
       Event sub = IEventMapper.MAPPER.createToEvent(s);

        given(eventService.create(Mockito.any())).willReturn(IEventMapper
                .MAPPER.eventToResponse(sub));

        mockMvc.perform(post("/api/event/create")
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(s)))
                .andDo(print())
                .andExpect(jsonPath("$.name", is(s.getName())))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin",password = "password",authorities = "ADMINISTRATOR")
    public void whenPostWithEmptyBody_thenReturnStatus400()
            throws Exception {

        mockMvc.perform(post("/api/event/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString("")))
                .andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser(username = "admin",password = "password",authorities = "ADMINISTRATOR")
    public void whenInvalidPath_thenReturnStatus404() throws Exception {
        mockMvc.perform(get("/api/event/badurl/dsio"))
                .andExpect(status().isNotFound());
    }
    @Test
    @WithMockUser(username = "admin",password = "password",authorities = "ADMINISTRATOR")
    public void whenDeleteWithBadId_thenReturnStatus400() throws Exception {
        mockMvc.perform(delete("/api/event/delete/-1")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser(username = "admin",password = "password",authorities = "ADMINISTRATOR")
    public void whenGetAll_thenListOfEventsAndStatus200() throws Exception {
        EventCreateDto e1 = new EventCreateDto(java.sql.Timestamp.valueOf("2021-09-23 10:10:10.0"), UUID.randomUUID(),UUID.randomUUID(),"group","name","auditory");
        EventCreateDto e2 = new EventCreateDto(java.sql.Timestamp.valueOf("2021-09-23 10:10:10.0"), UUID.randomUUID(),UUID.randomUUID(),"group","name","auditory");
        Event sub1 = IEventMapper.MAPPER.createToEvent(e1);
        Event sub2 = IEventMapper.MAPPER.createToEvent(e2);
        EventResponseDto sr1 = IEventMapper.MAPPER.MAPPER.eventToResponse(sub1);
        EventResponseDto sr2 = IEventMapper.MAPPER.MAPPER.eventToResponse(sub2);
        List<EventResponseDto> allEmployees = Arrays.asList(sr1,sr2);

        given(eventService.getAll()).willReturn(allEmployees);

        mockMvc.perform(get("/api/event")
                        .contentType("application/json")).andDo(print()).andExpect(jsonPath("$[0].name", is(e1.getName())))
                .andExpect(jsonPath("$[1].name", is(e2.getName())))
                .andExpect(status().isOk());
    }
}
