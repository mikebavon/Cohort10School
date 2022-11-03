package com.cohort10.actions;

import com.cohort10.controllers.SubjectBeanI;
import com.cohort10.model.Subject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/subject")
public class SubjectAction extends HttpServlet {

    @EJB
    SubjectBeanI subjectBean;

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Subject subject = new Subject();

        try {
            BeanUtils.populate(subject, req.getParameterMap());

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        if (StringUtils.isBlank(subject.getName())) {
            servletCtx.setAttribute("addSubjectError" , "Name is required<br/>");
            res.sendRedirect("./subject_add.jsp");
            return;
        }

        if (StringUtils.isBlank(subject.getCode())) {
            servletCtx.setAttribute("addSubjectError" , "Code is required<br/>");
            res.sendRedirect("./subject_add.jsp");
            return;
        }

        subjectBean.add(subject);

        res.sendRedirect("./home.jsp");

    }

}
