<%-- 
    Document   : director_notification
    Created on : Jul 16, 2020, 12:42:22 AM
    Author     : ngota
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="director_header.jsp" %>
        <main class="row justify-content-center">
            <h1>Notification page</h1>
            <div class="row py-4 justify-content-center col-12">
                <c:if test="${requestScope.NOTIFICATION != null}">
                    <c:if test="${not empty requestScope.NOTIFICATION}" var="checkNotificationList">
                        <table class="table text-center col-lg-10" border="1">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Description</th>
                                    <th>Commiter id</th>
                                    <th>Created time</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${requestScope.NOTIFICATION}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.notificationDescription}</td>
                                        <td>${dto.commiterID}</td>
                                        <td><fmt:formatDate value="${dto.createdTime}" pattern = "dd/MM/yyyy HH:mm"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${!pageScope.checkNotificationList}">
                        <h2 style="color: red">No notifications</h2>
                    </c:if>
                </c:if>
            </div>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
