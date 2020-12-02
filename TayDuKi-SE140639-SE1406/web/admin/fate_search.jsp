<%-- 
    Document   : fate_search
    Created on : Jul 4, 2020, 7:27:54 PM
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
        <main>
            <c:if test="${requestScope.DELETE_SUCCESS != null}">
                <div class="alert alert-success text-center mx-auto block w-25" role="alert">
                    ${requestScope.DELETE_SUCCESS}
                </div>
            </c:if>
            <div class="row mx-2 my-2 justify-content-center">
                <c:if test="${requestScope.SEARCH_RESULT != null}">
                    <c:if test="${not empty requestScope.SEARCH_RESULT}" var="checkFateList">
                        <table class="table text-center col-lg-10" border="1">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Start time</th>
                                    <th>End time</th>
                                    <th>Director id</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${requestScope.SEARCH_RESULT}" varStatus="counter">
                                    <tr>
                                        <td>${dto.fateID}</td>
                                        <td>${dto.fateName}</td>
                                        <td><fmt:formatDate value="${dto.fateStartTime}" pattern = "dd/MM/yyyy"/></td>
                                        <td><fmt:formatDate value="${dto.fateEndTime}" pattern = "dd/MM/yyyy"/></td>
                                        <td>
                                            <c:url value="SearchUserDetailController" var="directorDetailLink">
                                                <c:param name="txtSearch" value="${param.txtSearch}"/>
                                                <c:param name="txtUserID" value="${dto.fateDirectorID}"/>
                                                <c:param name="cbSearchType" value="${param.cbSearchType}"/>
                                            </c:url>
                                            <a href="${pageScope.directorDetailLink}">${dto.fateDirectorID}</a>
                                        </td>
                                        <td>
                                            <form action="SEARCH_FATE_DETAIL" method="POST">
                                                <input type="hidden" name="cbSearchType" value="${param.cbSearchType}"/>
                                                <input type="hidden" name="txtFateID" value="${dto.fateID}"/>
                                                <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                                <button class="btn btn-info" type="submit" value="Search detail Fate">
                                                    View detail
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </c:if>
                <c:if test="${!pageScope.checkFateList}">
                    <h2 style="color: red">No record found</h2>
                </c:if>
            </div>
            <ul class="pagination justify-content-center">
                <c:forEach begin="1" end="${requestScope.SEARCH_RESULT_PAGE_COUNT}" varStatus="counter">
                    <c:url value="SEARCH_FATE" var="searchPageLink">
                        <c:param name="cbSearchType" value="${param.cbSearchType}"/>
                        <c:param name="pageID" value="${counter.count}"/>
                        <c:param name="txtSearch" value="${param.txtSearch}"/>
                    </c:url>
                    <li class="page-item 
                        <c:if test="${requestScope.CURRENT_PAGE == counter.count}">
                            active
                        </c:if>">
                        <a class="page-link" href="${pageScope.searchPageLink}">${counter.count}</a>
                    </li>
                </c:forEach>
            </ul>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
