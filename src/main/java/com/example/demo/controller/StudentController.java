package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/test")
    public void test() {
        studentService.createStudent(null);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable(name = "id") Integer id,
                                 @RequestBody Student student) {
        student.setId(id);
        return studentService.updateStudent(student);
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/students/{name}")
    public Student getByName(@PathVariable("name") String name) {
        return studentService.getStudent(name);
    }

    @GetMapping("/students/{id}/subjects")
    public Subject getSubjectByStudent(@PathVariable(name = "id") Integer idStudent) {
        return studentService.getSubject(idStudent);
    }

    @GetMapping("/students/{id}/name")
    public String getFullNameByStudent(@PathVariable(name = "id") Integer id) {
        return studentService.getFullName(id);
    }

    @GetMapping("/students")
    public List<Student> getAllStudent() {
        return studentService.findALl();
    }

    @PutMapping("/students/{id}")
    public void updateFullName(@PathVariable(name = "id") Integer id,
                               @RequestParam(value = "newName") String newName) {
        studentService.updateStudentByJPQL(id, newName);
    }

    @GetMapping("/hql/students/{id}")
    public Student getStudentByHQL(@PathVariable("id") Integer id) {
        return studentService.getStudentByHQL(id);
    }

    @DeleteMapping("/delete/{id}")
    public void softDelete(@PathVariable(name = "id") Integer id) {
        studentService.deleteStudent(id);
    }
}
