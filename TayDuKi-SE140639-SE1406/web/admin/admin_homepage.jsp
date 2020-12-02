<%-- 
    Document   : admin_homepage
    Created on : Jun 30, 2020, 10:46:26 AM
    Author     : ngota
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="admin_header.jsp" %>
        <main class="text-center">
            <h1>Hello, ${sessionScope.USER_DTO.fullname}</h1>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
