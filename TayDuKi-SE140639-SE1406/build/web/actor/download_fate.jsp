<%-- 
    Document   : dowload_fate
    Created on : Jul 15, 2020, 7:29:00 PM
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
        <main class="row justify-content-center">
            <h1>Requirements file</h1>
            <div class="row py-4 justify-content-center col-12">
                <c:if test="${requestScope.REPORT != null}">
                    <c:if test="${not empty requestScope.REPORT}" var="checkReportList">
                        <table class="table text-center col-lg-10" border="1">
                            <thead>
                                <tr>
                                    <th>Fate ID</th>
                                    <th>Requirement file</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${requestScope.REPORT}" varStatus="counter">
                                    <tr>
                                        <td>${dto.fateID}</td>
                                        <td>
                                            <c:if test="${dto.requirementFile != null}">
                                                <c:url var="downloadLink" value="DOWNLOAD_FATE">
                                                    <c:param name="txtRequirementFile" value="${dto.requirementFile}"/>
                                                </c:url>
                                                <a href="${pageScope.downloadLink}">Download</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
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
