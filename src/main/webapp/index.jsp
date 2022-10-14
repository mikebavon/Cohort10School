<!DOCTYPE html>
<html>
    <head>
        <script type='text/javascript' src='./js/sample.js'></script>
    </head>
    <body>
        <%!
            int one = 1;
            int two = 2;

            String showNotes() {
                return " This is the best class ever!!";
            }
        %>

        <%!
            int one2 = 1;
            int two2 = 2;

            String showNotes2() {
                return " This is another best class ever!!";
            }
        %>

        <h1><%= application.getAttribute("applicationLabel") %></h1>

        <%
            int results = one+two;
            results = results * 10;

            if (results>3) {
        %>
            To Register <a href='./register.jsp'>Register</a><br/>
            To Login <a href='./login'>Login</a><br/>

        <% } else { %>

            To Register <a href='./register.jsp'>Register</a><br/>
            To Login <a href='./login'>Login</a><br/>

        <% } %>

        <h1><%= one+two %></h1>
        <h1><%= showNotes() %></h1>
        <h1><%= showNotes2() %></h1>

        <%
            out.print(one+two);

            out.print(request.getRequestURL());

        %>

    </body>
</html>