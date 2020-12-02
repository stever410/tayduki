<%-- 
    Document   : fate_history
    Created on : Jul 15, 2020, 4:55:32 PM
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
        <%@include file="actor_header.jsp" %>
        <main class="row justify-content-center">
            <h1>Fate history</h1>
            <div class="row py-4 justify-content-center col-12">
                <c:if test="${requestScope.REPORT != null}">
                    <c:if test="${not empty requestScope.REPORT}" var="checkReportList">
                        <table class="table text-center col-lg-10" border="1">
                            <thead>
                                <tr>
                                    <th>Fate ID</th>
                                    <th>Role name</th>
                                    <th>Role description</th>
                                    <th>Start time</th>
                                    <th>End time</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${requestScope.REPORT}" varStatus="counter">
                                    <tr>
                                        <td>${dto.fateID}</td>
                                        <td>${dto.roleName}</td>
                                        <td>${dto.roleDescription}</td>
                                        <td><fmt:formatDate value="${dto.startTime}" pattern = "dd/MM/yyyy"/></td>
                                        <td><fmt:formatDate value="${dto.endTime}" pattern = "dd/MM/yyyy"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <ul class="pagination justify-content-center col-12">
                            <c:forEach begin="1" end="${requestScope.REPORT_PAGE_COUNT}" varStatus="counter">
                                <c:url value="LOAD_FATE_HISTORY" var="reportLink">
                                    <c:param name="pageID" value="${counter.count}"/>
                                </c:url>
                                <li class="page-item 
                                    <c:if test="${requestScope.CURRENT_PAGE == counter.count}">
                                        active
                                    </c:if>">
                                    <a class="page-link" href="${reportLink}">${counter.count}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${!pageScope.checkReportList}">
                        <h2 style="color: red">No record found</h2>
                    </c:if>
                </c:if>
            </div>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
