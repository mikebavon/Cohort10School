package com.cohort10.customtags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class HeaderTag extends TagSupport {

    private String applicationLabel;

    private String color = "black";

    private Date date = new Date();

    public int doStartTag() {
        try {
            JspWriter out = pageContext.getOut();
            out.print("<!DOCTYPE html>\n" +
            "<html>\n" +
            "    <head>\n" +
            "        <link rel=\"stylesheet\" type=\"text/css\" href=\"./styles/app.css\"/>\n" +
            "    </head>\n" +
            "    <body><h1>" + applicationLabel + "</h1> The color is: " + color + " and today date is "
                    + DateFormat.getDateInstance().format(date) + "<br/>");

        } catch(IOException ioe) {
            System.out.println("Error in HeadingTag: " + ioe);
        }

        return(EVAL_BODY_INCLUDE); // Include tag body
    }

    public int doEndTag() {
        try {
            JspWriter out = pageContext.getOut();
            out.print("</SPAN></TABLE>");
        } catch(IOException ioe) {
            System.out.println("Error in HeadingTag: " + ioe);
        }
        return(EVAL_PAGE); // Continue with rest of JSP page
    }

    public String getApplicationLabel() {
        return applicationLabel;
    }

    public void setApplicationLabel(String applicationLabel) {
        this.applicationLabel = applicationLabel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}