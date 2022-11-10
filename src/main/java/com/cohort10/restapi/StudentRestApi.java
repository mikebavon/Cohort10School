package com.cohort10.restapi;

import com.cohort10.bean.StudentBean;
import com.cohort10.model.Student;

import java.util.List;

public class StudentRestApi {

    public List<Student> list() {

        return new StudentBean().list();

    }
}
