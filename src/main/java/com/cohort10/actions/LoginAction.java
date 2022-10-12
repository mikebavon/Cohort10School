package com.cohort10.actions;

import com.cohort10.common.Gender;
import com.cohort10.model.Student;
import com.cohort10.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebServlet(urlPatterns = "/login", initParams = {
    @WebInitParam(name = "username", value = "johannes@graz.com"),
    @WebInitParam(name = "password", value = "Cohort123*")
})
public class LoginAction extends HttpServlet {

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);

        servletCtx = config.getServletContext();

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

        User user = this.login(username, password);
        if (user == null || user.getId() == null) {
            wr.print(this.login("Invalid username & password combination<br/>"));
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("profile", user.getProfile());
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

    public User login(String username, String password) {

        User user = null;

        try {
            Connection connection = (Connection) servletCtx.getAttribute("dbConnection");
            Statement sqlStmt = connection.createStatement();

            ResultSet result = sqlStmt.executeQuery("select * from users where username='" + username + "' and " +
                    "password='" + password + "'");
            while (result.next()) {
                user = new User();
                user.setId((long) result.getInt("id"));
                user.setUsername(result.getString("username"));
            }

        }catch (Exception ex) {
            System.out.println("Log In Error: " + ex.getMessage());
        }

        return user;

    }
}
