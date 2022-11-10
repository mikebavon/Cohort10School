package com.cohort10.view;

import com.cohort10.bean.StudentBeanI;
import com.cohort10.model.Student;

import javax.ejb.EJB;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("StudentView")
public class StudentView implements Serializable {

    @EJB
    private StudentBeanI studentBean;

    public List<Student> getList(){
        return studentBean.list();
    }

}
