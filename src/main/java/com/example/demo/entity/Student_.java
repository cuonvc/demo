package com.example.demo.entity;

import com.example.demo.entity.*;
import lombok.Data;

import javax.annotation.processing.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Student.class)
public class Student_ {
    public static volatile SingularAttribute<Student, Integer> id;
    public static volatile SingularAttribute<Student, String> fullName;
    public static volatile SingularAttribute<Student, Boolean> isDeleted;
    public static volatile SingularAttribute<Student, Integer> age;
    public static volatile SingularAttribute<Student, LocalDate> createdDate;
    public static volatile SingularAttribute<Student, Subject> subjects;
    public static volatile SingularAttribute<Student, Teacher> teachers;
    public static volatile SingularAttribute<Student, School> schools;
    public static volatile SingularAttribute<Student, Major> majors;

    public static final String ID = "id";
    public static final String FULL_NAME = "fullName";
    public static final String IS_DELETED = "isDeleted";
    public static final String AGE = "age";
    public static final String CREATED_DATE = "createdDate";
}
