package com.cohort10.actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


@WebServlet(urlPatterns = "/login", initParams = {
    @WebInitParam(name = "username", value = "johannes@graz.com"),
    @WebInitParam(name = "password", value = "Cohort123*")
})
public class LoginAction extends HttpServlet {

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

        if (!username.equals(getServletConfig().getInitParameter("username")) && !password.equals(getServletConfig().getInitParameter("password"))) {
            wr.print(this.login("Invalid username & password combination<br/>"));
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("username", username);
        session.setAttribute("loggedInTime", "Logged In Time:" + new Date());

        RequestDispatcher dispatcher = req.getRequestDispatcher("./home");
        dispatcher.forward(req, res);

    }

    public String login(String actionError){
        return "<!DOCTYPE html>"
            + "<html> "
                + "<head> "
                + "</head>"
                + "<body>"
                    + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
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
