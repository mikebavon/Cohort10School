package com.cohort10.actions;

import com.cohort10.common.Gender;
import com.cohort10.model.Student;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/student")
public class StudentAction extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().print(this.addStudentView(null));
    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter wr = res.getWriter();

        Student student = new Student();

        try {
            BeanUtils.populate(student, req.getParameterMap());

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        if (StringUtils.isBlank(student.getName())) {
            wr.print(this.addStudentView("Name is required<br/>"));
            return;
        }

        if (StringUtils.isBlank(student.getRegNo())) {
            wr.print(this.addStudentView("Reg No is required<br/>"));
            return;
        }

        this.insert(student);

        RequestDispatcher dispatcher = req.getRequestDispatcher("./home");
        dispatcher.forward(req, res);

    }

    public String addStudentView(String actionError){
        return "<!DOCTYPE html>"
            + "<html> "
                + "<head> "
                + "</head>"
                + "<body>"
                    + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                    + "<h2> User Login</h2>"
                    + "<form action=\"./student\" method=\"post\">"
                        + "<table> "
                            + "<tr> <td> Student Name: </td> <td> <input type=\"text\" name=\"name\"> </td> </tr> "
                            + "<tr> <td> Student Reg No: </td> <td> <input type=\"text\" name=\"regNo\"> </td> </tr> "
                            + "<tr> <td> <input type=\"submit\" value=\"Submit\"></tr> "
                        + "</table>"
                    + "</form>"
                    + "<span style=\"color:red\">" + (actionError != null? actionError : "") + "</span><br/>"
                    + "Home? <a href='./home'>Register</a><br/>"
                + "</body>"
            + "</html>";
    }

    public void insert(Student student) {
        if (student == null || StringUtils.isBlank(student.getName()) || StringUtils.isBlank(student.getRegNo()))
            return;

        if (student.getGender() == null)
            student.setGender(Gender.NA);

        if (student.getDateOfBirth() == null)
            student.setDateOfBirth(new java.util.Date());

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "Okello3477#*");

            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate("insert into students(name,reg_no,date_of_birth,gender) " +
                "values('" + student.getName() + "','" + student.getRegNo() + "'," +
                    "'" + new Date(student.getDateOfBirth().getTime()) + "','" + student.getGender().name() + "')");

        }catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                connection.close();
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }

    }


}
