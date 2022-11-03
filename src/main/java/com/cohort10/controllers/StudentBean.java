package com.cohort10.controllers;

import com.cohort10.common.Gender;
import com.cohort10.model.Student;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
@Remote
public class StudentBean implements StudentBeanI {

    @PersistenceContext
    EntityManager em;

    public void add(Student student) {
        if (student == null || StringUtils.isBlank(student.getName()) || StringUtils.isBlank(student.getRegNo()))
            return;

        if (student.getGender() == null)
            student.setGender(Gender.NA);

        if (student.getDateOfBirth() == null)
            student.setDateOfBirth(new java.util.Date());

        em.merge(student);

    }

    public void update(Student student) {

    }

    public void delete(Student student) {

    }

    public List<Student> getList() {
        return em.createQuery("FROM Student s", Student.class).getResultList();
    }

    public void enroll(Student student) {

    }
}
