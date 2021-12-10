package com.example.faculty.repository;

import com.example.faculty.database.entity.Subject;
import com.example.faculty.database.repository.SubjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SubjectRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void whenFindById_thenReturnSubject() {
        Subject subject = new Subject("Test","Test","Test",1,1,"spring",null);
        entityManager.persist(subject);
        entityManager.flush();

        Subject found = subjectRepository.findById(subject.getId()).get();

        assertThat(subject).isEqualTo(found);
    }
    @Test
    public void whenGetAll_thenReturnListOfSubject() {
        Subject s1 = new Subject("Test1","Test","Test",1,1,"spring",null);
        Subject s2 = new Subject("Test2","Test","Test",1,1,"spring",null);
        Subject s3 = new Subject("Test3","Test","Test",1,1,"spring",null);
        entityManager.persist(s1);
        entityManager.persist(s2);
        entityManager.persist(s3);

        List<Subject> found = subjectRepository.findAll();
        assertThat(found).extracting(Subject::getName).contains(s1.getName(), s2.getName(), s3.getName());

    }
    @Test
    public void whenDelete_thenFindByIdReturnNull() {
        Subject s = new Subject("Test","Test","Test",1,1,"spring",null);
        entityManager.persist(s);
        entityManager.flush();

        subjectRepository.delete(s);
        Subject found = subjectRepository.findById(s.getId()).orElse(null);

        assertThat(found).isNull();
    }
    @Test
    public void whenUpdate_thenFindByIdReturnUpdated() {
        Subject s = new Subject("Test","Test","Test",1,1,"spring",null);
        entityManager.persist(s);
        entityManager.flush();
        Subject dbSubject = subjectRepository.findById(s.getId()).get();
        String newName = "NewName";
        dbSubject.setName(newName);
        subjectRepository.save(dbSubject);

        Subject found = subjectRepository.findById(s.getId()).orElse(null);

        assertThat(found.getName()).isEqualTo(newName);
    }
    @Test
    public void whenCreateWithInvalidParameter_thenExceptionIsThrown() {
        Subject subject = new Subject(null,"Test","Test",-1,1,"",null);

        assertThatThrownBy(() -> {  subjectRepository.saveAndFlush(subject);}).isInstanceOf(ConstraintViolationException.class);
    }
}