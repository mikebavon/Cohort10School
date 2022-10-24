package com.cohort10.customtags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class HeaderTag extends SimpleTagSupport {

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<!DOCTYPE html>\n" +
        "<html>\n" +
        "    <head>\n" +
        "        <link rel=\"stylesheet\" type=\"text/css\" href=\"./styles/app.css\"/>\n" +
        "    </head>\n" +
        "    <body>");
    }
}