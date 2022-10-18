<jsp:include page="header.jsp" />
To Register <a href='./register.jsp'>Register</a><br/>
To Login <a href='./login.jsp'>Login</a><br/>

<h2> Contact Us</h2>
<form action= "./contact_us.jsp" method="post">
     <table>
         <tr> <td> Email: </td> <td> <input type= "text" name= "contactEmail"> </td> </tr>
         <tr> <td> Phone: </td> <td> <input type= "text" name= "phoneNumber"> </td> </tr>
         <tr> <td> Message: </td> <td> <textarea type= "textarea" name= "message" rows="4" cols="50" ></textarea> </td> </tr>
         <tr> <td> <input type= "submit" value= "Submit"></tr>
     </table>
</form>

<br/> <br/> <br/> <br/>
<%= request.getParameter("invalidAccess") %> <br/>
<%= request.getParameter("invalidAccessWarn") %>
<jsp:include page="footer.jsp" />