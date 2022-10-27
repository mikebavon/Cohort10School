package com.cohort10.listeners;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;

@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent ctxe){
        ServletContext ctx = ctxe.getServletContext();
        ctx.setAttribute("applicationLabel", "eSchool | School Management System | Made In Kenya");
    }

    public void contextDestroyed(ServletContextEvent ctxe) {

    }

}
