package com.example.demo.service.impl;

import com.example.demo.entity.Subject;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.service.SubjectService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    @Transactional
    public Subject deleteById(Integer id) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("filterDelete");
        filter.setParameter("isDeleted", true);
        //
        Subject subject = entityManager.find(Subject.class, id);

        session.disableFilter("filterDelete");

        return subject;

    }
}
