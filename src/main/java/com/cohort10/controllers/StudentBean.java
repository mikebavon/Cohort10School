package com.cohort10.controllers;

import com.cohort10.common.Gender;
import com.cohort10.model.ParentStudent;
import com.cohort10.model.SchoolFee;
import com.cohort10.model.Student;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
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

    @EJB
    private SchoolFeeBeanI schoolFeeBean;

    public void add(Student student) throws Exception {
        if (student == null || StringUtils.isBlank(student.getPerson().getName()) || StringUtils.isBlank(student.getRegNo()))
            return;

        if (student.getGender() == null)
            student.setGender(Gender.NA);

        if (student.getDateOfBirth() == null)
            student.setDateOfBirth(new java.util.Date());

        if (!schoolFeeBean.checkFeePaid(new SchoolFee(student)))
            throw new Exception("School fees not paid!");

        em.merge(student);

    }

    public void delete(Long studentId) {
        em.remove(em.find(Student.class, studentId));

    }

    public List<Student> getList() {
        return em.createQuery("FROM Student s", Student.class).getResultList();
    }

    public void enroll(Student student) {

    }
}
