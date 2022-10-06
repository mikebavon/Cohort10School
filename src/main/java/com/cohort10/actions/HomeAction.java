package com.cohort10.actions;

import com.cohort10.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class HomeAction extends HttpServlet {

    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        res.getWriter().print("<!DOCTYPE html>"
            + "<html> "
                + "<head>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"./styles/app.css\"/>"
                + "</head>"
                + "<body>"
                    + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                    + "<span style=\"color:green;font-size: 24px;font-weight:bold\">Logged In</span>"
                    + "<br/>" + studentGrid((List<Student>) session.getAttribute("students"))
                    + "<br/>Logout <a href='./logout'>Logout</a><br/>"
                + "</body>"
            + "</html>");
    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session == null || session.getId() == null)
            res.sendRedirect("./");

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
                    + "<br/>" + studentGrid((List<Student>) session.getAttribute("students"))
                    + "<br/>Logout <a href='./logout'>Logout</a><br/>"
                + "</body>"
            + "</html>");
    }

    public String studentGrid(List<Student> students) {

        if (students == null)
            students = new ArrayList<Student>();

        String studentGrid = "<table >" +
            "<tr>" +
                "<th>Student Name</th>" +
                "<th>Student Reg Number</th>" +
                "<th></th>" +
            "</tr>";

        for (Student student : students)
            studentGrid += "<tr>"
               + "<td>" + student.getName() + "</td>"
               + "<td>" + student.getRegNo() + "</td>"
               + "<td><a href=\"./edit\">Edit</a>  | <a href=\"./delete\">Delete</a></td>"
              + "</tr>";

        studentGrid += "</table>";

        return studentGrid;

    }
}
