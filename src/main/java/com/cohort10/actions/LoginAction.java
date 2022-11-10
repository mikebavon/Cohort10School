package com.cohort10.actions;

import com.cohort10.bean.AuthBeanI;
import com.cohort10.model.Auth;
import com.cohort10.model.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
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

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);

        servletCtx = config.getServletContext();

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Auth auth = new Auth();

        try {
            BeanUtils.populate(auth, req.getParameterMap());

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        try {
            User user = authBean.login(auth);
            HttpSession session = req.getSession(true);
            session.setAttribute("username", user.getEmail());
            session.setAttribute("profile", user.getProfile());
            session.setAttribute("loggedInTime", " Logged In At: " + new Date());

            res.sendRedirect("./home.jsp");

        } catch (Exception ex) {
            servletCtx.setAttribute("loginError" , ex.getMessage());
            res.sendRedirect("./login.jsp");
        }

    }
}
