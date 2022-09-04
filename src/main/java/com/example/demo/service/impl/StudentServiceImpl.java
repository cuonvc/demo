package com.example.demo.service.impl;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.RepoTestHQL;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Service
//@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RepoTestHQL repoTestHQL;

//    public StudentServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository, EntityManager entityManager, StudentController studentController) {
//        this.studentRepository = studentRepository;
//        this.teacherRepository = teacherRepository;
//        this.entityManager = entityManager;
//    }

    @Override
    @Transactional
    public Student updateStudent(Student std) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //enable an filter
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("testFilter");
        filter.setParameter("test", true);

//        Student student = studentRepository.findById(std.getId()).get();
//        student.setFullName(std.getFullName());
//        studentRepository.save(student);
//        Set<Teacher> teachers = new HashSet<>();
//        teachers.add(teacherRepository.findById(20).orElseThrow(RuntimeException::new));
//
//        student.setTeachers(teachers);
//
//        studentRepository.save(student);

//        Student student = studentRepository.findById(std.getId()).get();  //persist state
//        Student student1 = studentRepository.findById(std.getId() + 1).get();
//        entityManager.clear();

        Student student2 = studentRepository.findById(std.getId()).get();
//        entityManager.detach(student2);
        student2.setFullName(std.getFullName() + "Test4");
//        entityManager.merge(student2);
//        entityManager.persist(student2);

        //error
//        Student student1 = studentRepository.findById(std.getId()).get(); //new student persist
//        student.setFullName(std.getFullName());
//        student1.setFullName(std.getFullName() + "tsst");

//        System.out.println(student.hashCode() + ":" + student1.hashCode());  //difference
//        entityManager.merge(student);  //merge to insite persist

        //@transacsional will be auto commit to DB
        return null;
    }

    @Override
    @Transactional
    public Student createStudent(Student student) {

        student.setFullName("Test");
//        entityManager.persist(student);

        return null;
    }

    @Override
    public Student getStudent(String name) {
        Student student = studentRepository.findStudentByName(name).orElseThrow(RuntimeException::new);
        return student;
    }

    @Override
    public Subject getSubject(Integer idStudent) {
        Subject subjectByStudent = studentRepository.findSubjectByStudentId(idStudent)
                .orElseThrow(RuntimeException::new);
        subjectByStudent.setStudents(null);
        return subjectByStudent;
    }

    @Override
    public String getFullName(Integer id) {
        return studentRepository.findNameByStudentId(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void updateStudentByJPQL(Integer id, String newName) {
        Student student = studentRepository.findById(id).get();
        studentRepository.updateFullName(student, newName);
    }

    @Override
    public Student getStudentByHQL(Integer id) {
        return repoTestHQL.getStudent(id);
    }
}
