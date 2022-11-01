package com.cohort10.controllers;

import com.cohort10.model.Subject;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named("subjectController")
public class SubjectController implements Serializable,SubjectControllerI {

    @Resource(lookup = "java:jboss/datasources/School")
    DataSource dataSource;


    private List<Subject> list;

    public void add(Subject subject) {
        if (subject == null || StringUtils.isBlank(subject.getName()) || StringUtils.isBlank(subject.getCode()))
            return;

        try {

            Statement sqlStmt = dataSource.getConnection().createStatement();
            sqlStmt.executeUpdate("insert into subjects(name,code) " +
                "values('" + subject.getName() + "','" + subject.getCode() + "')");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void update(Subject subject) {

    }

    public void delete(Subject subject) {

    }

    public List<Subject> getList() {

        List<Subject> subjects = new ArrayList<Subject>();

        try {
            Statement sqlStmt = dataSource.getConnection().createStatement();

            ResultSet result = sqlStmt.executeQuery("select * from subjects");
            while (result.next()) {
                Subject subject = new Subject();
                subject.setName(result.getString("name"));
                subject.setCode(result.getString("code"));

                subjects.add(subject);
            }

        }catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return subjects;

    }

    public void setList(List<Subject> list) {
        this.list = list;
    }

}
