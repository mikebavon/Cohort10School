package com.cohort10.controllers;

import com.cohort10.common.Gender;
import com.cohort10.model.Student;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentController implements Serializable {

    public void add(Connection connection, Student student) {
        if (student == null || StringUtils.isBlank(student.getName()) || StringUtils.isBlank(student.getRegNo()))
            return;

        if (student.getGender() == null)
            student.setGender(Gender.NA);

        if (student.getDateOfBirth() == null)
            student.setDateOfBirth(new java.util.Date());

        try {

            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate("insert into students(name,reg_no,date_of_birth,gender) " +
                    "values('" + student.getName() + "','" + student.getRegNo() + "'," +
                    "'" + new Date(student.getDateOfBirth().getTime()) + "','" + student.getGender().name() + "')");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void update(Student student) {

    }

    public void delete(Student student) {

    }

    public List<Student> list(Connection connection, Student filter) {
        List<Student> students = new ArrayList<Student>();

        try {
            Statement sqlStmt = connection.createStatement();

            ResultSet result = sqlStmt.executeQuery("select * from students");
            while (result.next()) {
                com.cohort10.model.Student student = new com.cohort10.model.Student();
                student.setName(result.getString("name"));
                student.setRegNo(result.getString("reg_no"));
                student.setGender(Gender.valueOf(result.getString("gender")));
                student.setDateOfBirth(result.getDate("date_of_birth"));

                students.add(student);
            }

        }catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return students;

    }

}
