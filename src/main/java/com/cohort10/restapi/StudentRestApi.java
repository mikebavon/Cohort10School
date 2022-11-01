package com.cohort10.restapi;

import com.cohort10.controllers.StudentController;
import com.cohort10.model.Student;

import java.util.List;

public class StudentRestApi {

    public List<Student> list() {

        return new StudentController().getList();

    }
}
