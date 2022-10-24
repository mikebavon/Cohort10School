<%@ page isELIgnored="false" %>
<%@ taglib prefix="jc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

${param.invalidAccess}
${param.invalidAccessWarn}

<jch:Cohort />
<jsp:include page="footer.jsp" />