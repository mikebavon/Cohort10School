package com.cohort10.example;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Hello implements Servlet {

    ServletConfig config = null;

    public void init(ServletConfig config) throws ServletException {
        System.out.println("***********Hello world is initialized.. place into service*************8");
        this.config = config;

    }

    public ServletConfig getServletConfig() {
        return config;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("The servlet Name in web.xml: " + getServletConfig().getServletName());
        res.setContentType("text/html");

        String action = req.getParameter("action");

        PrintWriter wr = res.getWriter();
        if (action != null && action.equalsIgnoreCase("register"))
            wr.print(this.register());
        else if (action != null && action.equalsIgnoreCase("login"))
            wr.print(this.login());
        else
            wr.print(this.home());
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("***********Destroying hello world servlet*************");
    }

    public String home(){
        return "<!DOCTYPE html>"
            + "<html> "
                + "<head> "
                    + "<h1> Welcome to School Management system</h1>"
                + "</head>"
                + "<body>"
                    + " To Register <a href='./welcome?action=register'>Register</a><br/>"
                    + " To Loging <a href='./welcome?action=login'>Login</a><br/>"
                + "</body>"
            + "</html>";
    }

    public String register(){
        return "<!DOCTYPE html>"
            + "<html> "
                + "<head> "
                    + "<h1> Registration</h1>"
                + "</head>"
                + "<body>"
                    + "<form>"
                        + "<table> "
                            + "<tr> <td> Email: </td> <td> <input type=”text” email=”email”> </td> </tr> "
                            + "<tr> <td> Password: </td> <td> <input type=”Password” name=”password”> </td> </tr> "
                            + "<tr> <td> Confirm Password: </td> <td> <input type=”Password” name=”confirmPassword”> </td> </tr> "
                        + "</table>"
                    + "</form>"
                + "</body>"
            + "</html>";
    }

    public String login(){
        return "<!DOCTYPE html>"
            + "<html> "
                + "<head> "
                    + "<h1> Login</h1>"
                + "</head>"
                + "<body>"
                    + "<form>"
                        + "<table> "
                            + "<tr> <td> Email/Username: </td> <td> <input type=”text” email=”username”> </td> </tr> "
                            + "<tr> <td> Password: </td> <td> <input type=”Password” name=”password”> </td> </tr> "
                        + "</table>"
                    + "</form>"
                + "</body>"
            + "</html>";
    }
}
