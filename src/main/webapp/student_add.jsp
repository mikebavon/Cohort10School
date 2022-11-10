<%@ page isELIgnored="false" %>
<cht:Header applicationLabel="${applicationScope.applicationLabel}" color="red"/>
<h2>Add Student</h2>
<form action="./student" method="post">
    <table>
        <tr> <td> Student Name: </td> <td> <input type="text" name="person.name"> </td> </tr>
        <tr> <td> Student ID No: </td> <td> <input type="text" name="person.idNo"> </td> </tr>
        <tr> <td> Student Gender: </td> <td> <input type="text" name="person.gender"> </td> </tr>
        <tr> <td> Student Reg No: </td> <td> <input type="text" name="regNo"> </td> </tr>
        <tr> <td> <input type="submit" value="Submit"></tr>
    </table>
</form>
<%
    String loginError = (String) application.getAttribute("addStudentError");

    if (loginError != null && !loginError.equals("")) {

%>
    <span style="color:red">${applicationScope.addStudentError}</span><br/>

<% } %>

Register? <a href='./home.jsp'>Home</a><br/>
<cft:Footer> This is the best system done by Cohort 10 </cft:Footer>