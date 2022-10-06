package com.cohort10.actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/student")
public class Student  extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().print(this.addStudent(null));
    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter wr = res.getWriter();

        String name = req.getParameter("name");

        if (name == null || name.equalsIgnoreCase("")) {
            wr.print(this.addStudent("Name is required<br/>"));
            return;
        }

        HttpSession session = req.getSession();
        List<String> students = (List<String>) session.getAttribute("students");

        if (students == null)
            students = new ArrayList<String>();

        students.add(name);
        session.setAttribute("students", students);

        RequestDispatcher dispatcher = req.getRequestDispatcher("./home");
        dispatcher.forward(req, res);

    }

    public String addStudent(String actionError){
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
                            + "<tr> <td> <input type=\"submit\" value=\"Submit\"></tr> "
                        + "</table>"
                    + "</form>"
                    + "<span style=\"color:red\">" + (actionError != null? actionError : "") + "</span><br/>"
                    + "Home? <a href='./home'>Register</a><br/>"
                + "</body>"
            + "</html>";
    }
}
