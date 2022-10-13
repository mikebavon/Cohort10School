package com.cohort10.listeners;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

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
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/school");
            dataSource.setUsername("root");
            dataSource.setPassword("Okello3477#*");
            dataSource.setInitialSize(3);
            dataSource.setMaxIdle(3);
            dataSource.setMaxTotal(5);

            Connection connection = dataSource.getConnection();
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
