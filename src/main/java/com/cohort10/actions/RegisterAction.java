package com.cohort10.actions;

import com.cohort10.bean.UserBeanI;
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
import java.io.IOException;

@WebServlet("/register")
public class RegisterAction extends HttpServlet {

    @EJB
    UserBeanI userBean;

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);

        servletCtx = config.getServletContext();

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        User user = new User();

        try {
            BeanUtils.populate(user, req.getParameterMap());

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        try {
            userBean.register(user);
            res.sendRedirect("./login.jsp");

        } catch (Exception ex) {
            servletCtx.setAttribute("registerError" , ex.getMessage());
            res.sendRedirect("./register.jsp");
        }

    }
}
