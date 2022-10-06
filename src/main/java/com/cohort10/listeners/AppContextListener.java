package com.cohort10.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent ctxe){
        System.out.print("eSchool Application Starting up....initializing default attributes");
        ServletContext ctx = ctxe.getServletContext();
        ctx.setAttribute("applicationLabel", "eSchool | School Management System | Made In Kenya");

    }

    public void contextDestroyed(ServletContextEvent ctxe) {
        System.out.print("eSchool Application stopped.");
    }

}
