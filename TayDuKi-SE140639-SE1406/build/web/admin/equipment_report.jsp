<%-- 
    Document   : equipment_report
    Created on : Jul 14, 2020, 8:18:02 PM
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
        <%@include file="admin_header.jsp" %>
        <main class="row justify-content-center">
            <div class="card">
                <form class="form-inline mx-3 my-3" action="SEARCH_EQUIPMENT_REPORT" method="POST">
                    <label for="startTime">Start Time</label>
                    <input type="text" 
                           class="form-control mx-4" 
                           id="fateStartTime" 
                           name="txtStartTime" 
                           placeholder="EX: 15/07/2020"
                           required="true"
                           value="<fmt:formatDate value="${requestScope.START_TIME}" pattern = "dd/MM/yyyy"/>"/>
                    <label for="endTime">End Time</label>
                    <input type="text" 
                           class="form-control mx-4" 
                           id="fateStartTime" 
                           name="txtEndTime" 
                           placeholder="EX: 15/07/2020"
                           required="true"
                           value="<fmt:formatDate value="${requestScope.END_TIME}" pattern = "dd/MM/yyyy"/>"/>
                    <label class="mr-4">Status</label>
                    <input type="radio" name="rdStatus" value="true" 
                           <c:if test="${requestScope.STATUS}">
                               checked="true"
                           </c:if>/>
                    <label class="mr-4">True</label>
                    <input type="radio" name="rdStatus" value="false" 
                           <c:if test="${!requestScope.STATUS}">
                               checked="true"
                           </c:if>>
                    <label class="mr-4">False</label>
                    <button class="btn btn-primary" value="Filter">
                        Filter equipment
                    </button>
                    <input type="hidden" name="pageID" value="1"/>
                </form>
                <c:if test="${requestScope.ERROR != null}">
                    <div class="alert alert-warning text-center mx-auto block w-50" role="alert">
                        ${requestScope.ERROR}
                    </div>
                </c:if>
            </div>
            <div class="row py-4 justify-content-center col-12">
                <c:if test="${requestScope.REPORT != null}">
                    <c:if test="${not empty requestScope.REPORT}" var="checkReportList">
                        <table class="table text-center col-lg-10" border="1">
                            <thead>
                                <tr>
                                    <th>Equipment ID</th>
                                    <th>Fate ID</th>
                                    <th>Add date</th>
                                    <th>Director id</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${requestScope.REPORT}" varStatus="counter">
                                    <tr>
                                        <td>${dto.equipmentID}</td>
                                        <td>${dto.fateID}</td>
                                        <td><fmt:formatDate value="${dto.addDate}" pattern = "dd/MM/yyyy"/></td>
                                        <td>${dto.directorID}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <ul class="pagination justify-content-center col-12">
                            <c:forEach begin="1" end="${requestScope.REPORT_PAGE_COUNT}" varStatus="counter">
                                <c:url value="SEARCH_EQUIPMENT_REPORT" var="reportLink">
                                    <c:param name="pageID" value="${counter.count}"/>
                                    <c:param name="txtStartTime" value="${param.txtStartTime}"/>
                                    <c:param name="txtEndTime" value="${param.txtEndTime}"/>
                                    <c:param name="rdStatus" value="${param.rdStatus}"/>
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
