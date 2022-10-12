package com.cohort10.actions;

import com.cohort10.common.Gender;
import com.cohort10.model.Student;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class HomeAction extends HttpServlet {

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().print("<!DOCTYPE html>"
            + "<html> "
                + "<head>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"./styles/app.css\"/>"
                + "</head>"
                + "<body>"
                    + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                    + "<span style=\"color:green;font-size: 24px;font-weight:bold\">Logged In</span>"
                    + "<br/>" + studentGrid(new Student())
                    + "<br/>Logout <a href='./logout'>Logout</a><br/>"
                + "</body>"
            + "</html>");
    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        res.getWriter().print("<!DOCTYPE html>"
            + "<html> "
                + "<head> "
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"./styles/app.css\"/>"
                + "</head>"
                + "<body>"
                    + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                    + "<h2> Welcome: " + session.getAttribute("username") + "  Logged In At: " + session.getAttribute("loggedInTime") + "</h2>"
                    + "<span style=\"color:green;font-size: 24px;font-weight:bold\">Logged In</span>"
                    + "<br/>Logout <a href='./student'>Add Student</a><br/>"
                    + "<br/>" + studentGrid(new Student())
                    + "<br/>Logout <a href='./logout'>Logout</a><br/>"
                + "</body>"
            + "</html>");
    }

    public String studentGrid(Student studentFilter) {
        List<Student> students = new ArrayList<Student>();

        try {
            Connection connection = (Connection) servletCtx.getAttribute("dbConnection");
            Statement sqlStmt = connection.createStatement();

            ResultSet result = sqlStmt.executeQuery("select * from students");
            while (result.next()) {
                Student student = new Student();
                student.setName(result.getString("name"));
                student.setRegNo(result.getString("reg_no"));
                student.setGender(Gender.valueOf(result.getString("gender")));
                student.setDateOfBirth(result.getDate("date_of_birth"));

                students.add(student);
            }

        }catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        String studentGrid = "<table >" +
            "<tr>" +
                "<th>Student Name</th>" +
                "<th>Student Reg Number</th>" +
                "<th>Gender</th>" +
                "<th>Date Of Birth</th>" +
                "<th></th>" +
            "</tr>";

        for (Student student : students)
            studentGrid += "<tr>"
               + "<td>" + student.getName() + "</td>"
               + "<td>" + student.getRegNo() + "</td>"
               + "<td>" + student.getGender().getName() + "</td>"
               + "<td>" + student.getDateOfBirth() + "</td>"
               + "<td><a href=\"./edit\">Edit</a>  | <a href=\"./delete\">Delete</a></td>"
              + "</tr>";

        studentGrid += "</table>";

        return studentGrid;

    }
}
