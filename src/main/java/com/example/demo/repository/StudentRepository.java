package com.example.demo.repository;

import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, CustomRepo {

    @Query(value = "SELECT distinct s from Student s JOIN FETCH s.subjects j WHERE s.fullName =:fullName")
    Optional<Student> findStudentByName(String fullName);

    @Query(value = "SELECT s.subjects FROM Student s WHERE s.id =:id")
    Optional<Subject> findSubjectByStudentId(Integer id);

    @Query(value = "SELECT s.fullName FROM Student s WHERE s.id =:id")
    Optional<String> findNameByStudentId(Integer id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Student s SET s.fullName = ?2 WHERE s = ?1")
    void updateFullName(Student student, String name);
}
