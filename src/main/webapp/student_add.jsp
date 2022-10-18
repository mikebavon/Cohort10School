<jsp:include page="header.jsp" />
<h2>Add Student</h2>
<form action="./student" method="post">
    <table>
        <tr> <td> Student Name: </td> <td> <input type="text" name="name"> </td> </tr>
        <tr> <td> Student Reg No: </td> <td> <input type="text" name="regNo"> </td> </tr>
        <tr> <td> <input type="submit" value="Submit"></tr>
    </table>
</form>
<%
    String loginError = (String) application.getAttribute("addStudentError");

    if (loginError != null && !loginError.equals("")) {

%>
    <span style="color:red"> <%= application.getAttribute("addStudentError") %> </span><br/>

<% } %>

Register? <a href='./home.jsp'>Home</a><br/>
<jsp:include page="footer.jsp" />