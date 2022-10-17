<%@ page import="com.cohort10.controllers.StudentController" %>
<%@ page import="com.cohort10.model.Student" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%! StudentController studentController = new StudentController(); %>

<%@ include file="header.jsp" %>

<h2> Welcome: <%= session.getAttribute("username") %> Logged In At: <%= session.getAttribute("loggedInTime") %></h2>
<span style="color:green;font-size: 24px;font-weight:bold">Logged In</span>
<br/>Add Student <a href='./student_add.jsp'>Add Student</a><br/>
<br/>
<table>
<tr>
    <th>Student Name</th>
    <th>Student Reg Number</th>
    <th>Gender</th>
    <th>Date Of Birth</th>
    <th></th>
</tr>
<%
    List<Student> students = studentController.list((Connection) application.getAttribute("dbConnection"), new Student());

    for (Student student : students) {

%>
    <tr>
        <td><%= student.getName() %></td>
        <td><%= student.getRegNo() %></td>
        <td><%= student.getGender().getName() %></td>
        <td><%= student.getDateOfBirth() %></td>
        <td><a href="./edit">Edit</a>  | <a href="./delete">Delete</a></td>
    </tr>

<% } %>

</table>

<br/>Logout <a href='./logout'>Logout</a><br/>

<%@ include file="footer.jsp" %>
