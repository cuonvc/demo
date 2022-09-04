package com.example.demo;


import com.example.demo.entity.Major;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.MajorRepository;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class TeacherTestJpa {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private MajorRepository majorRepository;

    @Test
    public void testCreateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFullName("teacher 1");
        teacher.setAge(30);
        teacher.setSchool(schoolRepository.findById(3).get());   //error ?

        teacherRepository.save(teacher);
    }

    @Test
    public void testUpdateTeacher() {
        Teacher teacher = teacherRepository.findById(1).get();
        Set<Major> majors = new HashSet<>();

        teacher.setFullName("Edit full name");
        teacher.setSchool(schoolRepository.findById(1).get());

        majors.add(majorRepository.findById(1).get());
        teacher.setMajors(majors);

        teacher.setSchool(schoolRepository.findById(2).get());

        teacherRepository.save(teacher);
    }
}
