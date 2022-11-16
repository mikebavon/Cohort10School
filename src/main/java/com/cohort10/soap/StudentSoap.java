package com.cohort10.soap;

import com.cohort10.bean.StudentBeanI;
import com.cohort10.model.Student;

import javax.ejb.EJB;
import javax.jws.WebService;
import java.util.List;

@WebService
public class StudentSoap {

    @EJB
    private StudentBeanI studentBean;

    public void add(Student student) {
        try {
            studentBean.add(student);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public List<Student> list() {
        return studentBean.list();
    }
}
