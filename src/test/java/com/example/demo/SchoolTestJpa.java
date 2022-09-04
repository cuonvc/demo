package com.example.demo;

import com.example.demo.entity.Major;
import com.example.demo.entity.School;
import com.example.demo.entity.Student;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class SchoolTestJpa {
    @Autowired
    private SchoolRepository schoolRepository;

    @Test
    public void testCreateSchool() {
        School school = new School();
        school.setSchoolName("School 4");
        schoolRepository.save(school);
    }

    @Test
    public void testGetSchool() {
        School school = schoolRepository.findById(3).get();
        System.out.println("School ID: " + school.getId());
        System.out.println("School name: " + school.getSchoolName());
        System.out.print("Major: ");
        school.getMajors().forEach(major -> System.out.print(major.getMajorName() + ", "));
//        System.out.print("\nTeacher: ");
//        school.getTeachers().forEach(teacher -> System.out.print(teacher.getFullName() + ", "));
//        System.out.print("\nStudent: ");
//        school.getStudents().forEach(student -> System.out.print(student.getFullName() + ", "));
    }

    @Test
    public void testUpdateSchool() {
        School school = schoolRepository.findById(3).get();

        school.setSchoolName("Edit name");
        schoolRepository.save(school);
    }

    @Test
    @Transactional
    public void testDeleteSchool() {
        School school = schoolRepository.findById(2).get();
        schoolRepository.delete(school);
    }
}
