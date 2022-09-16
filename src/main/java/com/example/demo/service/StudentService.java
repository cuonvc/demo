package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;

import java.util.List;

public interface StudentService {

    Student updateStudent(Student student);
    Student createStudent(Student student);
    Student getStudent(String name);
    Subject getSubject(Integer idStudent);
    String getFullName(Integer id);
    List<Student> findALl();
    void updateStudentByJPQL(Integer id, String newName);

    Student getStudentByHQL(Integer id);
    void deleteStudent(Integer id);
}
