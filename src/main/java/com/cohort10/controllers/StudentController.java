package com.cohort10.controllers;

import com.cohort10.common.Gender;
import com.cohort10.model.Student;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Named("highSchool")
@RequestScoped
public class StudentController implements Serializable, StudentControllerI {

    @Resource(lookup = "java:jboss/datasources/School")
    DataSource dataSource;

    private List<Student> list;

    public void add(Student student) {
        if (student == null || StringUtils.isBlank(student.getName()) || StringUtils.isBlank(student.getRegNo()))
            return;

        if (student.getGender() == null)
            student.setGender(Gender.NA);

        if (student.getDateOfBirth() == null)
            student.setDateOfBirth(new java.util.Date());

        try {

            Statement sqlStmt = dataSource.getConnection().createStatement();
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

    public List<Student> getList() {

        List<Student> students = new ArrayList<Student>();

        try {
            Statement sqlStmt = dataSource.getConnection().createStatement();

            ResultSet result = sqlStmt.executeQuery("select * from students");
            while (result.next()) {
                Student student = new Student();
                student.setId((long) result.getInt("id"));
                student.setName(result.getString("name"));
                student.setRegNo(result.getString("reg_no"));
                student.setGender(Gender.valueOf(result.getString("gender")));
                student.setDateOfBirth(result.getDate("date_of_birth"));

                students.add(student);
            }

        }catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        for (Student student : students) {
            System.out.println(student.getName());
        }

        return students;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public void enroll(Student student) {

    }
}
