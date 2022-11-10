package com.cohort10.bean;

import com.cohort10.model.Subject;

import java.util.List;

public interface SubjectBeanI {

    void add(Subject subject);

    List<Subject> list();
}
