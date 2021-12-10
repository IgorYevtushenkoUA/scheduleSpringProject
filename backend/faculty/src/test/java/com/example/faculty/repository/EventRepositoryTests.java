package com.example.faculty.repository;

import com.example.faculty.database.entity.Event;
import com.example.faculty.database.entity.Subject;
import com.example.faculty.database.entity.User;
import com.example.faculty.database.enums.UserRole;
import com.example.faculty.database.repository.EventRepository;
import com.example.faculty.database.repository.SubjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Test
    public void whenFindById_thenReturnEvent() {
        User u = new User("name","surname","email","passwod","username","parental","about",1,"FI",null,null,null,UserRole.STUDENT);
        entityManager.persist(u);
        entityManager.flush();
        Event event = new Event(java.sql.Timestamp.valueOf("2021-09-23 10:10:10.0"),null,null,null);
        event.setAuditory("auditory");
        event.setGroup("group");
        event.setName("name");
        event.setUser(u);
        entityManager.persist(event);
        entityManager.flush();

        Event found = eventRepository.findById(event.getId()).get();

        assertThat(event).isEqualTo(found);
    }
    @Test
    public void whenGetAll_thenReturnListOfEvents() {
        User u = new User("name","surname","email","passwod","username","parental","about",1,"FI",null,null,null,UserRole.STUDENT);
        entityManager.persist(u);
        entityManager.flush();
        Event event1 = new Event(java.sql.Timestamp.valueOf("2021-09-23 10:10:10.0"),null,null,null);
        event1.setAuditory("auditory");
        event1.setGroup("group");
        event1.setName("name");
        event1.setUser(u);
        Event event2 = new Event(java.sql.Timestamp.valueOf("2021-09-23 10:10:10.0"),null,null,null);
        event2.setAuditory("auditory");
        event2.setGroup("group");
        event2.setName("name");
        event2.setUser(u);
        Event event3 = new Event(java.sql.Timestamp.valueOf("2021-09-23 10:10:10.0"),null,null,null);
        event3.setAuditory("auditory");
        event3.setGroup("group");
        event3.setName("name");
        event3.setUser(u);
        entityManager.persist(event1);
        entityManager.persist(event2);
        entityManager.persist(event3);

        List<Event> found = eventRepository.findAll();
        // then
        assertThat(found).extracting(Event::getId).contains(event1.getId(), event2.getId(), event3.getId());

    }
    @Test
    public void whenDelete_thenFindByIdReturnNull() {
        User u = new User("name","surname","email","passwod","username","parental","about",1,"FI",null,null,null,UserRole.STUDENT);
        entityManager.persist(u);
        entityManager.flush();
        Event e = new Event(java.sql.Timestamp.valueOf("2021-09-23 10:10:10.0"),null,null,null);
        e.setAuditory("auditory");
        e.setGroup("group");
        e.setName("name");
        e.setUser(u);
        entityManager.persist(e);
        entityManager.flush();

        eventRepository.delete(e);
        Event found = eventRepository.findById(e.getId()).orElse(null);

        assertThat(found).isNull();
    }
    @Test
    public void whenUpdate_thenFindByIdReturnUpdated() {
        User u = new User("name","surname","email","passwod","username","parental","about",1,"FI",null,null,null,UserRole.STUDENT);
        entityManager.persist(u);
        entityManager.flush();
        Event e = new Event(java.sql.Timestamp.valueOf("2021-09-23 10:10:10.0"),null,null,null);
        e.setAuditory("auditory");
        e.setGroup("group");
        e.setName("name");
        e.setUser(u);
        entityManager.persist(e);
        entityManager.flush();
        Event dbEvent = eventRepository.findById(e.getId()).get();
        String newAuditory = "111";
        dbEvent.setAuditory(newAuditory);
        eventRepository.save(dbEvent);

        Event found = eventRepository.findById(e.getId()).orElse(null);

        assertThat(found.getAuditory()).isEqualTo(newAuditory);
    }


    public void whenCreateWithInvalidParameter_thenExceptionIsThrown() {
        Event e = new Event(null,null,null,null);
        assertThatThrownBy(() -> {  eventRepository.save(e);}).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void whenFindEventForUserByYearAndMonthAndDay_thenReturnListOfEvents() {

       List<Event> found = eventRepository.findEventByYearAndMonthAndDay(2021,12,1);
        assertThat(found).hasSize(3);
    }
    @Test
    public void whenFindEventForUserByYearAndMonth_thenReturnListOfEvents() {

        List<Event> found = eventRepository.findEventForUserByYearAndMonth(2021,12);
        assertThat(found).hasSize(2);
    }
    @Test
    public void whenFindEventBySubject_thenReturnListOfEvents() {
        List<Event> found = eventRepository.findAllBySubject(UUID.fromString("9abf6df0-4c8a-11ec-81d3-0242ac130003"));
        assertThat(found).hasSize(4);
    }

}
