<%-- 
    Document   : detail
    Created on : Jul 1, 2020, 7:15:05 PM
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
        <%@include file="actor_header.jsp" %>
        <main class="row">
            <div class="col-sm-12 col-md-6 mx-2 text-center">     
                <img class="img-fluid" src="images/user/${requestScope.DTO.userImage}">
            </div>
            <div class="col-sm-12 col-md-5 mx-2 text-center">
                <h4 class="mb-3">${requestScope.DTO.userID} INFO</h4>
                <ul class="list-group">
                    <li class="list-group-item">Role: ${requestScope.DTO.roleID}</li>
                    <li class="list-group-item">Fullname: ${requestScope.DTO.fullname}</li>
                    <li class="list-group-item">Sex: ${requestScope.DTO.sex}</li>
                    <li class="list-group-item">Phone: ${requestScope.DTO.phoneNum}</li>
                    <li class="list-group-item">Email: ${requestScope.DTO.email}</li>
                    <li class="list-group-item">Description: ${requestScope.DTO.userDescription}</li>
                </ul>
            </div>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
