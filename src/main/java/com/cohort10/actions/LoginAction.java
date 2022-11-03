package com.cohort10.actions;

import com.cohort10.controllers.AuthBeanI;
import com.cohort10.controllers.TestAlternativeI;
import com.cohort10.model.User;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;


@WebServlet(urlPatterns = "/login")
public class LoginAction extends HttpServlet {

    @EJB
    AuthBeanI authBean;

    @Inject
    TestAlternativeI testAlternative;

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);

        servletCtx = config.getServletContext();

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        testAlternative.display();

        String password = req.getParameter("password");
        String username = req.getParameter("username");

        if (username == null || username.equalsIgnoreCase("")) {
            servletCtx.setAttribute("loginError" , "Username is required<br/>");
            res.sendRedirect("./login.jsp");
            return;
        }

        if (password == null || password.equalsIgnoreCase("")) {
            servletCtx.setAttribute("loginError" , "Password is required<br/>");
            res.sendRedirect("./login.jsp");
            return;
        }

        User user = authBean.login(username, password);
        if (user == null || user.getId() == null) {
            servletCtx.setAttribute("loginError" , "Password is username & password combination<br/>");
            res.sendRedirect("./login.jsp");
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("profile", user.getProfile());
        session.setAttribute("loggedInTime", " Logged In At: " + new Date());

        res.sendRedirect("./home.jsp");

    }
}
