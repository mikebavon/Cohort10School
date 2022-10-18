<jsp:include page="header.jsp" />
<h2>Add Student</h2>
<form action="./subject" method="post">
    <table>
        <tr> <td> Subject Name: </td> <td> <input type="text" name="name"> </td> </tr>
        <tr> <td> Subject Code: </td> <td> <input type="text" name="code"> </td> </tr>
        <tr> <td> <input type="submit" value="Submit"></tr>
    </table>
</form>
<%
    String loginError = (String) application.getAttribute("addSubjectError");

    if (loginError != null && !loginError.equals("")) {

%>
    <span style="color:red"> <%= application.getAttribute("addSubjectError") %> </span><br/>

<% } %>

Register? <a href='./home.jsp'>Home</a><br/>
<jsp:include page="footer.jsp" />