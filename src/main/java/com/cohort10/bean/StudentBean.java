package com.cohort10.bean;

import com.cohort10.common.Gender;
import com.cohort10.model.Person;
import com.cohort10.model.SchoolFee;
import com.cohort10.model.Student;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Stateless
@Remote
public class StudentBean implements StudentBeanI {

    @PersistenceContext
    EntityManager em;

    @EJB
    private SchoolFeeBeanI schoolFeeBean;

    public void add(Student student) throws Exception {
        if (student == null || student.getPerson() == null || StringUtils.isBlank(student.getPerson().getName())
                || StringUtils.isBlank(student.getRegNo()))
            return;

        if (student.getPerson().getGender() == null)
            student.getPerson().setGender(Gender.NA);

        if (student.getPerson().getDateOfBirth() == null)
            student.getPerson().setDateOfBirth(new java.util.Date());

        //if (!schoolFeeBean.checkFeePaid(new SchoolFee(student)))
         //   throw new Exception("School fees not paid!");

        em.merge(student);

    }

    public List<Student> list() {
        return em.createQuery("SELECT new Student(s.id,s.regNo,s.countMale,s.countFemale,s.countNa) FROM Student s",
            Student.class).getResultList();
    }

    //FROM Student s === SELECT s FROM Student s
    //SELECT s.id FROM Student s
    //SELECT s.person.name FROM Student s

    //SELECT new Student(s.person) FROM Student s

}
