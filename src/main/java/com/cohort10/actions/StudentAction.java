package com.cohort10.actions;

import com.cohort10.controllers.HighSchoolStudent;
import com.cohort10.controllers.StudentControllerI;
import com.cohort10.controllers.UniversityStudent;
import com.cohort10.model.Student;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student")
public class StudentAction extends HttpServlet {

    @Inject
    @Named("university")
    StudentControllerI studentController;

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Student student = new Student();

        try {
            BeanUtils.populate(student, req.getParameterMap());

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        if (StringUtils.isBlank(student.getName())) {
            servletCtx.setAttribute("addStudentError" , "Name is required<br/>");
            res.sendRedirect("./student_add.jsp");
            return;
        }

        if (StringUtils.isBlank(student.getRegNo())) {
            servletCtx.setAttribute("addStudentError" , "Reg No is required<br/>");
            res.sendRedirect("./student_add.jsp");
            return;
        }

        studentController.add(student);

        res.sendRedirect("./home.jsp");

    }

}
