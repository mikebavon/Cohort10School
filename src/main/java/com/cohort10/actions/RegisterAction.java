package com.cohort10.actions;

import com.cohort10.common.Gender;
import com.cohort10.model.Student;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet("/register")
public class RegisterAction extends HttpServlet {    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);

        servletCtx = config.getServletContext();

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().print(this.registerView(null));
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter wr = res.getWriter();

        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String email = req.getParameter("email");

        String actionError = "";
        if (email == null || email.equalsIgnoreCase(""))
            actionError = "Email is required<br/>";

        if (password == null || password.equalsIgnoreCase(""))
            actionError += "Password is required<br/>";

        if (confirmPassword == null || confirmPassword.equalsIgnoreCase(""))
            actionError += "Confirm password is required<br/>";

        if (password != null && confirmPassword != null && !password.equals(confirmPassword))
            actionError += "Password & confirm password do not match<br/>";

        if (actionError.equals("")) {
            this.insert(email, password);
            res.sendRedirect("./login");
        } else
            wr.print(this.registerView(actionError));
    }

    public String registerView(String actionError){

        return "<!DOCTYPE html>"
            + "<html> "
                + "<head> "
                + "</head>"
                + "<h1>" + getServletContext().getAttribute("applicationLabel") + "</h1>"
                + "<h2> User Registration </h2>"
                + "<body>"
                    + "<form action=\"./register\" method=\"post\">"
                        + "<table> "
                            + "<tr> <td> Email: </td> <td> <input type=\"text\" name=\"email\"> </td> </tr> "
                            + "<tr> <td> Password: </td> <td> <input type=\"Password\" name=\"password\"> </td> </tr> "
                            + "<tr> <td> Confirm Password: </td> <td> <input type=\"Password\" name=\"confirmPassword\"> </td> </tr> "
                            + "<tr> <td> <input type=\"submit\" value=\"Submit\"></tr> "
                        + "</table>"
                    + "</form>"
                    + "<span style=\"color:red\">" + (actionError != null? actionError : "") + "</span><br/>"
                    + "Login?<a href='./login'>Login</a><br/>"
                + "</body>"
            + "</html>";
    }

    public void insert(String username, String password) {
        try {
            Connection connection = (Connection) servletCtx.getAttribute("dbConnection");

            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate("insert into users(username,password) " +
                "values('" + username.trim() + "','" + password + "')");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

}
