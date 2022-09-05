package com.example.demo;


import com.example.demo.entity.Subject;
import com.example.demo.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class SubjectTestJpa {

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void testCreateSubject() {
        Subject subject = new Subject();
        subject.setSubjectName("subject 2");
        subjectRepository.save(subject);
    }

    @Test
    public void testUpdateSubject() {
        Subject subject = subjectRepository.findById(1).get();
        subject.setSubjectName("Edit name subject 1");
    }
}
