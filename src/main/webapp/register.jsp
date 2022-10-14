<!DOCTYPE html>
<html>
    <head>
    </head>
    <h1><%= application.getAttribute("applicationLabel") %></h1>
    <h2> User Registration </h2>
    <body>
        <form action="./register" method="post">
            <table>
                <tr> <td> Email: </td> <td> <input type="text" name="email"> </td> </tr>
                <tr> <td> Password: </td> <td> <input type="Password" name="password"> </td> </tr>
                <tr> <td> Confirm Password: </td> <td> <input type="Password" name="confirmPassword"> </td> </tr>
                <tr> <td> <input type="submit" value="Submit"></tr>
            </table>
        </form>

        <%
            String registerError = (String) application.getAttribute("registerError");

            if (registerError != null && !registerError.equals("")) {

        %>
            <span style="color:red"> <%= application.getAttribute("registerError") %> </span><br/>

        <% } %>

        Login?<a href='./login.jsp'>Login</a><br/>
    </body>
</html>