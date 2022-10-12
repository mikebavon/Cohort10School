package com.cohort10.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;

@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent ctxe){
        System.out.print("eSchool Application Starting up....initializing default attributes");
        ServletContext ctx = ctxe.getServletContext();
        ctx.setAttribute("applicationLabel", "eSchool | School Management System | Made In Kenya");

        try {
            System.out.print("Establishing connections....");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school",
                    "root", "Okello3477#*");
            ctx.setAttribute("dbConnection", connection);
            System.out.print("Connection Established....");
        } catch (Exception ex) {
            System.out.println("Connection Note Established....: " + ex.getMessage());

        }

    }

    public void contextDestroyed(ServletContextEvent ctxe) {

        try {
            ServletContext ctx = ctxe.getServletContext();
            Connection connection = (Connection) ctx.getAttribute("dbConnection");
            connection.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
