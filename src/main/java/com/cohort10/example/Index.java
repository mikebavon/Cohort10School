package com.cohort10.example;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Index extends HttpServlet {

    ServletConfig config = null;

    public void init(ServletConfig config) throws ServletException{
        this.config = config;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().print("<!DOCTYPE html>"
            + "<html> "
                + "<script type=\"text/javascript\" src=\"./js/sample.js\"></script>"
                + "<body>"
                + "<head> "
                    + "<h1>" + config.getServletContext().getInitParameter("applicationLabel") + "</h1>"
                + "</head>"
                    + " To Register <a href='./register'>Register</a><br/>"
                    + " To Login <a href='./login'>Login</a><br/>"
                + "</body>"
            + "</html>");
    }
}
