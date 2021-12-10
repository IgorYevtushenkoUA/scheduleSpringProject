package com.example.faculty.service;

import com.example.faculty.database.dto.event.EventResponseDto;
import com.example.faculty.database.entity.Event;
import com.example.faculty.database.repository.EventRepository;
import com.example.faculty.services.implementations.EventServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTests {

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventRepository eventRepository;

    @Before
    public void setUp() {
        Event event1 = new Event(java.sql.Timestamp.valueOf("2021-09-23 10:10:10.0"),null,null,null);
        event1.setId(UUID.nameUUIDFromBytes("event1_id".getBytes()));
        Event event2 = new Event(java.sql.Timestamp.valueOf("2021-09-23 16:47:21.0"),null,null,null);
        event2.setId(UUID.nameUUIDFromBytes("event2_id".getBytes()));
        Event event3 = new Event(java.sql.Timestamp.valueOf("2021-09-13 11:28:30.0"),null,null,null);
        event3.setId(UUID.nameUUIDFromBytes("event3_id".getBytes()));
        Event event4 = new Event(java.sql.Timestamp.valueOf("2021-10-18 11:28:30.0"),null,null,null);
        event4.setId(UUID.nameUUIDFromBytes("event4_id".getBytes()));

        List<Event> allEvents = Arrays.asList(event1,event2,event3,event4);
        List<Event> allEventsByYearMonthDay = Arrays.asList(event1,event2);
        List<Event> allEventsByYearMonth = Arrays.asList(event1,event2,event3);

        Mockito.when(eventRepository.findEventForUserByYearAndMonth(2021,9)).thenReturn(allEventsByYearMonth);
        Mockito.when(eventRepository.findEventByYearAndMonthAndDay(2021,9,23)).thenReturn(allEventsByYearMonthDay);
        Mockito.when(eventRepository.findAll()).thenReturn(allEvents);
        Mockito.when(eventRepository.findById(UUID.nameUUIDFromBytes("TEST UUID".getBytes()))).thenReturn(Optional.empty());
        Mockito.when(eventRepository.findById(event1.getId())).thenReturn(Optional.of(event1));
    }
    @Test
    public void whenInvalidId_thenEventShouldNotBeFound(){
        boolean fromDb = eventService.get(UUID.nameUUIDFromBytes("TEST UUID".getBytes())).isPresent();

        assertThat(fromDb).isEqualTo(false);
    }
    @Test
    public void whenValidId_thenEventShouldBeFound(){
        EventResponseDto fromDb = eventService.get(UUID.nameUUIDFromBytes("event1_id".getBytes())).get();

        assertThat(fromDb.getDatetime()).isEqualTo(java.sql.Timestamp.valueOf("2021-09-23 10:10:10.0"));
    }
    @Test
    public void whenGetAll_thenReturnListOfEvents(){
        Event event1 = new Event(java.sql.Timestamp.valueOf("2021-09-23 10:10:10.0"),null,null,null);
        event1.setId(UUID.nameUUIDFromBytes("event1_id".getBytes()));
        Event event2 = new Event(java.sql.Timestamp.valueOf("2021-09-23 16:47:21.0"),null,null,null);
        event2.setId(UUID.nameUUIDFromBytes("event2_id".getBytes()));
        Event event3 = new Event(java.sql.Timestamp.valueOf("2021-09-13 11:28:30.0"),null,null,null);
        event3.setId(UUID.nameUUIDFromBytes("event3_id".getBytes()));
        Event event4 = new Event(java.sql.Timestamp.valueOf("2021-10-18 11:28:30.0"),null,null,null);
        event4.setId(UUID.nameUUIDFromBytes("event4_id".getBytes()));

        List<EventResponseDto> fromDb = eventService.getAll();

        assertThat(fromDb).extracting(EventResponseDto::getId).contains(event1.getId(),event2.getId(),event3.getId(),event4.getId());

    }

    @Test
    public void whenFindEventForUserByYearAndMonthAndDay_thenReturnListOfEvents(){
        Event event1 = new Event(java.sql.Timestamp.valueOf("2021-09-23 10:10:10.0"),null,null,null);
        event1.setId(UUID.nameUUIDFromBytes("event1_id".getBytes()));
        Event event2 = new Event(java.sql.Timestamp.valueOf("2021-09-23 16:47:21.0"),null,null,null);
        event2.setId(UUID.nameUUIDFromBytes("event2_id".getBytes()));

        List<Event> fromDb = eventService.findEventByYearAndMonthAndDay(2021,9,23);

        assertThat(fromDb).extracting(Event::getId).contains(event1.getId(),event2.getId());
    }
    @Test
    public void whenFindEventForUserByYearAndMonth_thenReturnListOfEvents(){
        Event event1 = new Event(java.sql.Timestamp.valueOf("2021-09-23 10:10:10.0"),null,null,null);
        event1.setId(UUID.nameUUIDFromBytes("event1_id".getBytes()));
        Event event2 = new Event(java.sql.Timestamp.valueOf("2021-09-23 16:47:21.0"),null,null,null);
        event2.setId(UUID.nameUUIDFromBytes("event2_id".getBytes()));
        Event event3 = new Event(java.sql.Timestamp.valueOf("2021-09-13 11:28:30.0"),null,null,null);
        event3.setId(UUID.nameUUIDFromBytes("event3_id".getBytes()));

        List<Event> fromDb = eventService.findEventForUserByYearAndMonth(2021,9);

        assertThat(fromDb).extracting(Event::getId).contains(event1.getId(),event2.getId(),event3.getId());
    }
}
