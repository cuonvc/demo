package com.example.demo.service.impl;

import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.entity.Student_;
import com.example.demo.repository.RepoTestHQL;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.StudentService;
import org.apache.log4j.Logger;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.time.LocalDate;
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

    static Logger logger = Logger.getLogger(StudentService.class.getName());

    @Override
    @Transactional
    public Student updateStudent(Student std) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
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

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);
        Join<Student,Subject> studentSubjectJoin = studentRoot.join(Student_.SUBJECTS, JoinType.LEFT);
//        studentRoot.fetch(Student_.subjects);
//        studentRoot.fetch(Student_.school);

        System.out.println(studentSubjectJoin.get("subject_name"));

//        distict vs group by
        //List<Feticate>

//        List<Predicate>
//        List<Expression> expressions
//        List<Selection> selection = new ArrayList<>();

        entityManager.createQuery(criteriaQuery)
                .getResultStream()
                .forEach(s -> System.out.println(s.getSubjects()));

        return null;




        //or
//        Metamodel metamodel = entityManager.getMetamodel();
//        EntityType<Student> Student_ = metamodel.entity(Student.class);

        //...

//        Student std = studentRepository.save(student);
//        std.setCreatedDate(LocalDate.now());
//
//        return std;
    }

    @Override
    public Student getStudent(String name) {
//        Student student = studentRepository.findStudentByName(name).orElseThrow(RuntimeException::new);

//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
//        Root<Student> studentRoot = criteriaQuery.from(Student.class);
//
//        criteriaQuery.select(studentRoot).where(studentRoot.get(Student_.fullName).in(name));
//        TypedQuery<Student> query = entityManager.createQuery(criteriaQuery);
//
//        Student student = query.getSingleResult();



        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
            Root<Student> studentRoot = criteriaQuery.from(Student.class);

            criteriaQuery.select(studentRoot).where(studentRoot.get(Student_.fullName).in(name));
            TypedQuery<Student> query = entityManager.createQuery(criteriaQuery);

            Student student = query.getSingleResult();
            logger.info("Get student....");
            return student;
        } catch (RuntimeException exception) {
            logger.error("Student not found !");
            return null;
        }

//        logger.info("test logging");
//        logger.info(student.getFullName());
//        logger.warn("test Warrning");

//        return student;
    }

    @Override
    //using CriteriaBuilder
    public Subject getSubject(Integer idStudent) {
//        Subject subjectByStudent = studentRepository.findSubjectByStudentId(idStudent)
//                .orElseThrow(RuntimeException::new);
//        subjectByStudent.setStudents(null);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);

        criteriaQuery.select(studentRoot).where(studentRoot.get(Student_.id).in(idStudent));
        TypedQuery<Student> query = entityManager.createQuery(criteriaQuery);
        Student student = query.getSingleResult();

        Set<Subject> subjects = student.getSubjects();
        Subject[] arrSubJ = subjects.toArray(new Subject[subjects.size()]);
        return arrSubJ[0];
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

    @Override
    //test with CriteriaBuilder
    public List<Student> findALl() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);

        criteriaQuery.select(studentRoot);
        TypedQuery<Student> query = entityManager.createQuery(criteriaQuery);
        List<Student> allStudents = query.getResultList();

        return allStudents;
    }

    @Override
    public void deleteStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        studentRepository.delete(student);
    }
}
