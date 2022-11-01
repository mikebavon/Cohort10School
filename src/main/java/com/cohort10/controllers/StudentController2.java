package com.cohort10.controllers;

import com.cohort10.common.Gender;
import com.cohort10.model.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@UniversityStudent
public class StudentController2 implements Serializable, StudentControllerI {

    public void add(Student student) {
        List<Student> students = new ArrayList<Student>();
        
        students.add(new Student("Mike", "s13", new Date(), Gender.MALE));
        students.add(new Student("Saka", "s13", new Date(), Gender.MALE));
        students.add(new Student("Jane", "s12", new Date(), Gender.FEMALE));

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + students.size());

    }
}
