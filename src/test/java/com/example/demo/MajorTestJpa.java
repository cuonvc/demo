package com.example.demo;

import com.example.demo.entity.Major;
import com.example.demo.entity.School;
import com.example.demo.repository.MajorRepository;
import com.example.demo.repository.SchoolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MajorTestJpa {

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Test
    @Transactional(rollbackFor = {Exception.class})
    public void createMajor() {
        Major major = new Major();
        major.setMajorName("major 2");

        majorRepository.delete(majorRepository.findById(9).get());   //delete

        major.setSchool(schoolRepository.findById(3).get());  //error - id not found

        majorRepository.save(major);
    }

    @Test
    @Transactional
    public void testUpdateMajor() {
        Major major = majorRepository.findById(3).get();
        major.setMajorName("Edit name");
        major.setSchool(schoolRepository.findById(3).get());

        majorRepository.save(major);
    }
}
