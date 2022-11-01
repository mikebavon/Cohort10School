<%@ page isELIgnored="false" %>
<%@ page import="com.cohort10.model.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="jc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jfn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cht" uri="WEB-INF/tlds/header.tld" %>
<%@ taglib prefix="cft" uri="WEB-INF/tlds/footer.tld" %>

<cht:Header applicationLabel="${applicationScope.applicationLabel}" />
<h2> Welcome: ${sessionScope.username} ${sessionScope.loggedInTime}</h2>
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

<jc:forEach items="${studentController.list}" var="student">
    <tr>
        <td>${student.name}</td>
        <td>${student.regNo}</td>
        <td>${student.gender}</td>
        <td>${student.dateOfBirth}</td>
        <td><a href="./edit?id=1">Edit</a>  | <a href="./delete">Delete</a></td>
    </tr>
</jc:forEach>

</table>

<br/>Add Subject <a href='./subject_add.jsp'>Add Subject</a><br/>
<h1> Subjects </h1>
<table class='gridTable'>
<tr>
    <th>Subject Name</th>
    <th>Subject Code</th>
    <th>Notes</th>
    <th></th>
</tr>
<jc:forEach items="${subjectController.list}" var="subject">
    <tr>
        <td>${subject.name}</td>
        <td>${subject.code}</td>
        <td>
            <jc:choose>
                <jc:when test="${subject.code == 1}"> Kenyan </jc:when>
                <jc:when test="${subject.code == 2}"> Not Kenyan </jc:when>
                <jc:otherwise>Not Known</jc:otherwise>
            </jc:choose>
        </td>
        <td><a href="./edit">Edit</a>  | <a href="./delete">Delete</a></td>
    </tr>
</jc:forEach>

</table>

<br/>Logout <a href='./logout'>Logout</a><br/>

<cft:Footer> This is the best system done by Cohort 10 </cft:Footer>
