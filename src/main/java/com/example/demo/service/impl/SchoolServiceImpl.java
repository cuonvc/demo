package com.example.demo.service.impl;

import com.example.demo.entity.School;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.ScheduledTaskHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private EntityManager entityManager;

    public void createAll() {
        for (int i = 1; i <= 1000; i++) {
            School school = new School();
            school.setSchoolName("name " + i);
            school.setCreatedDate(LocalDate.now());
            schoolRepository.save(school);
        }

        //check result query in console
    }

    @Override
    public void getAll() {
        String paramDemoFromCli = "";
        schoolRepository.findAll();
        //if using fetch.LAZY (proxy status) -> n+1 problem
        //check result query in  console - 10001 queries executed (Fetch.EAGER, auto join tables)

        //Solution?
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaBuilder.parameter(School.class, "demoParam");
        CriteriaQuery<School> criteriaQuery = criteriaBuilder.createQuery(School.class);
        Root<School> schoolRoot = criteriaQuery.from(School.class);

        Query query = entityManager.createQuery(criteriaQuery)
                .setParameter("demoParam", paramDemoFromCli);
    }
}
