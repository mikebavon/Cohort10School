package com.cohort10.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Login extends HttpServlet {

    ServletConfig config = null;

    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().print(this.login(null));
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter wr = res.getWriter();

        String password = req.getParameter("password");
        String username = req.getParameter("username");

        if (username == null || username.equalsIgnoreCase("")) {
            wr.print(this.login("Username is required<br/>"));
            return;
        }

        if (password == null || password.equalsIgnoreCase("")) {
            wr.print(this.login("Password is required<br/>"));
            return;
        }

        if (!username.equals(config.getInitParameter("username")) && !password.equals(config.getInitParameter("password"))) {
            wr.print(this.login("Invalid username & password combination<br/>"));
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("loggedInTime", "Logged In Time:" + new Date());

        List<String> studentNames  = new ArrayList<String>();
        studentNames.add("Bonnie");
        studentNames.add("Simon");
        studentNames.add("James");
        studentNames.add("Mercy");
        studentNames.add("George");

        session.setAttribute("students", studentNames);

        RequestDispatcher dispatcher = req.getRequestDispatcher("./home");
        dispatcher.forward(req, res);

    }

    public String login(String actionError){
        return "<!DOCTYPE html>"
            + "<html> "
                + "<head> "
                + "</head>"
                + "<body>"
                    + "<h1>" + config.getServletContext().getInitParameter("applicationLabel") + "</h1>"
                    + "<h2> User Login</h2>"
                    + "<form action=\"./login\" method=\"post\">"
                        + "<table> "
                            + "<tr> <td> Email/Username: </td> <td> <input type=\"text\" name=\"username\"> </td> </tr> "
                            + "<tr> <td> Password: </td> <td> <input type=\"Password\" name=\"password\"> </td> </tr> "
                            + "<tr> <td> <input type=\"submit\" value=\"Submit\"></tr> "
                        + "</table>"
                    + "</form>"
                    + "<span style=\"color:red\">" + (actionError != null? actionError : "") + "</span><br/>"
                    + "Register? <a href='./register'>Register</a><br/>"
                + "</body>"
            + "</html>";
    }
}
