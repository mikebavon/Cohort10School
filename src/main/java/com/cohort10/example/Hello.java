package com.cohort10.example;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Hello implements Servlet {

    public void init(ServletConfig config) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");

        PrintWriter wr = res.getWriter();
        wr.print("<html>");
        wr.print("<head>");
        wr.print("<title>Example</title>");
        wr.print("</head>");
        wr.print("<body>");
        wr.print("<p>The best team......leaders of EPL - And everyone must join..</p>");
        wr.print("</body>");
        wr.print("</html>");

    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
