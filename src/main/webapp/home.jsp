<%@ page import="com.cohort10.model.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<jsp:useBean id="studentController" class="com.cohort10.controllers.StudentController" />
<jsp:useBean id="subjectController" class="com.cohort10.controllers.SubjectController" />

<jsp:include page="header.jsp" />

<h2> Welcome: <%= session.getAttribute("username") %> Logged In At: <%= session.getAttribute("loggedInTime") %></h2>
<span style="color:green;font-size: 24px;font-weight:bold">Logged In</span>
<br/>Add Student <a href='./student_add.jsp'>Add Student</a><br/>
<br/>
<h1> Students </h1>
<table class='gridTable'>
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
        <td><a href="./edit?id=<%= student.getId() %>">Edit</a>  | <a href="./delete">Delete</a></td>
    </tr>

<% } %>

</table>


<br/>Add Subject <a href='./subject_add.jsp'>Add Subject</a><br/>
<h1> Subjects </h1>
<table class='gridTable'>
<tr>
    <th>Subject Name</th>
    <th>Subject Code</th>
    <th></th>
</tr>
<%
    List<Subject> subjects = subjectController.list((Connection) application.getAttribute("dbConnection"), new Subject());

    for (Subject subject : subjects) {

%>
    <tr>
        <td><%= subject.getName() %></td>
        <td><%= subject.getCode() %></td>
        <td><a href="./edit">Edit</a>  | <a href="./delete">Delete</a></td>
    </tr>

<% } %>

</table>

<br/>Logout <a href='./logout'>Logout</a><br/>

<jsp:include page="footer.jsp" />
