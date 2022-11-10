package com.cohort10.bean;

import com.cohort10.model.Student;

import java.util.List;

public interface StudentBeanI {

    void add(Student student) throws Exception;

    List<Student> list();

}
