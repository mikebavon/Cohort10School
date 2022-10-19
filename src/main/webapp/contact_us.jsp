<%@ page isELIgnored="false" %>
<jsp:include page="header.jsp" />

<h2> Contact Us Message</h2>
<jsp:useBean id="contactUs" class="com.cohort10.model.ContactUs" />
<jsp:setProperty name="contactUs" property="*" />

<h1>
    Thank You <br/>
    Email: ${contactUs.contactEmail} <br/>
    Phone: ${contactUs.messageAvailable} <br/>
    for contacting us.
</h1>

<jsp:include page="footer.jsp" />

