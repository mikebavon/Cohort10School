package com.cohort10.example;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Home extends HttpServlet {
    ServletConfig config = null;

    public void init(ServletConfig config) throws ServletException{
        this.config = config;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter wr = res.getWriter();
        wr.print("<!DOCTYPE html>"
            + "<html> "
                + "<script type=\"text/javascript\" src=\"./js/sample.js\"></script>"
                + "<head> "
                    + "<h1> Welcome to " + config.getServletContext().getInitParameter("applicationLabel") + "</h1>"
                + "</head>"
                + "<body>"
                    + " To Register <a href='./welcome?action=register'>Register</a><br/>"
                    + " To Loging <a href='./welcome?action=login'>Login</a><br/>"
                + "</body>"
            + "</html>");
    }
}
