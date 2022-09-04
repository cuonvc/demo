//package com.example.demo;
//
//
//import com.example.demo.entity.Student;
//import com.example.demo.entity.Subject;
//import com.example.demo.entity.Teacher;
//import com.example.demo.repository.*;
//import com.example.demo.service.StudentService;
//import com.example.demo.service.impl.StudentServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.data.jpa.provider.HibernateUtils;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.interceptor.TransactionAspectSupport;
//
//import javax.persistence.EntityManager;
//import java.sql.SQLException;
//import java.util.HashSet;
//import java.util.Set;
//
//@DataJpaTest
//@Rollback(value = false)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class StudentTestJpa {
//
//    @Autowired
//    private StudentRepository studentRepository;
////
////    @Autowired
////    private SchoolRepository schoolRepository;
////
////    @Autowired
////    private MajorRepository majorRepository;
//
////    @Autowired
////    private TeacherRepository teacherRepository;
//
////    @Autowired
////    private SubjectRepository subjectRepository;
//
////    @Autowired
//    private StudentService studentService = new StudentServiceImpl();
//
////    @Test
////    @Transactional(rollbackFor = {SQLException.class})
////    public void testCreateStudent() {
////        Student student = new Student();
////        Set<Teacher> teachers = new HashSet<>();
////
////        student.setFullName("student 3");
////        student.setAge(20);
////        student.setSchool(schoolRepository.findById(3).get());
////        student.setMajor(majorRepository.findById(3).get());  //
////
//////        teachers.add(teacherRepository.findById(2).get());
//////        student.setTeachers(teachers);
////        studentRepository.save(student);
////    }
//
//    @Test
//    @Transactional
//    public void testUpdateStudent() {
//        Student student = new Student();
//        student.setId(2);
//        studentService.updateStudent(student);
//    }
//}
