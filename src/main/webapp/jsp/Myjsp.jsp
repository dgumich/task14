<%@ page import="web.MyEJB" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Mysjsp</title>
    </head>

    <body>

        <% String name = request.getParameter("parametrname");%>
        <% MyEJB myEJB = new MyEJB(); %>
        <%=myEJB.getForJSP(name)%>

    </body>

</html>
