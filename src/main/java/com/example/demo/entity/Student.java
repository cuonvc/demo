package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.sql.Update;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.metamodel.SingularAttribute;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Data
@Getter
@Setter
//@ToString
@FilterDef(name = "filterTest",  //filter single
        parameters = @ParamDef(name = "isDeleted", type = "boolean"),
        defaultCondition = "is_deleted =:isDeleted"
)
@FilterDef(name = "commonFilter",  //filter common
        parameters = {
                @ParamDef(name = "minDate", type = "java.time.LocalDate"),
                @ParamDef(name = "maxDate", type = "java.time.LocalDate")
        }
)
@FilterDef(name = "filterMinId",  //filter join table
        parameters = {
                @ParamDef(name = "minId", type = "Integer")
        }
)
@Filter(name = "filterTest")
@Filter(name = "commonFilter", condition = "createdDate >= :minDate and createdDate <= :maxDate")
//@org.hibernate.annotations.NamedQueries(
//        @org.hibernate.annotations.NamedQuery(
//                name="test",
//                query = "from Student s"
//        )
//)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
@SQLDelete(sql = "UPDATE Students SET is_deleted = true WHERE id=?")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String fullName;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column
    private Integer age;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    @FilterJoinTable(name = "filterMinId", condition = "student_id >= :minId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Set<Subject> subjects = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "student_teacher",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Major major;

    public Student(String fullName) {
        this.fullName = fullName;
    }
}