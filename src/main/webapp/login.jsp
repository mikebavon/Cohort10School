<%@ page isELIgnored="false" %>
<cht:Header applicationLabel="${applicationScope.applicationLabel}" />
<h2> User Login</h2>
<form action= "./login" method="post">
     <table>
        <cform:input name=""
         <tr> <td> Email/Username: </td> <td> <input type= "text " name= "username"> </td> </tr>
         <tr> <td> Password: </td> <td> <input type= "Password" name= "password"> </td> </tr>
         <tr> <td> <input type= "submit" value= "Submit"></tr>
     </table>
</form>
<%
    String loginError = (String) application.getAttribute("loginError");

    if (loginError != null && !loginError.equals("")) {

%>
    <span style="color:red">${applicationScope.loginError}</span><br/>

<% } %>

Register? <a href='./register.jsp'>Register</a><br/>
<cft:Footer> This is the best system done by Cohort 10 </cft:Footer>