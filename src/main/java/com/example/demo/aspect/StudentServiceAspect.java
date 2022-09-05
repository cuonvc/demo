package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Aspect
@Component
public class StudentServiceAspect {

    @Autowired
    private EntityManager entityManager;

    @Pointcut("execution(* com.example.demo.service..*(..))") //tất cả những method trong service package
    // sẽ được thực thi hàm beforeAdvice()
    private void selectGetName() {}

    @Before("selectGetName()")
    public void beforeAdvice() {
        entityManager.unwrap(Session.class)
                .enableFilter("filterDelete")
                .setParameter("isDeleted", false);
    }
}