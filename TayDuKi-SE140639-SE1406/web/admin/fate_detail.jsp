<%-- 
    Document   : fate_detail
    Created on : Jul 4, 2020, 8:58:05 PM
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
        <c:if test="${requestScope.UPDATE_SUCCESS != null}">
            <div class="alert alert-success text-center mx-auto block w-25 mt-5" role="alert">
                ${requestScope.UPDATE_SUCCESS}
            </div>
        </c:if>
        <main class="row justify-content-center">
            <div class="text-center">
                <h4 class="mb-3">${requestScope.DTO.fateID} INFO</h4>
                <ul class="list-group">
                    <li class="list-group-item">Name: ${requestScope.DTO.fateName}</li>
                    <li class="list-group-item">Description: ${requestScope.DTO.fateDescription}</li>
                    <li class="list-group-item">Shoot place: ${requestScope.DTO.fateShootPlace}</li>
                    <li class="list-group-item">Start time: <fmt:formatDate value="${requestScope.DTO.fateStartTime}" pattern = "dd/MM/yyyy"/></li>
                    <li class="list-group-item">End time: <fmt:formatDate value="${requestScope.DTO.fateEndTime}" pattern = "dd/MM/yyyy"/></li>
                    <li class="list-group-item">Shoot count: ${requestScope.DTO.fateShootCount}</li>
                    <li class="list-group-item">Requirement file: ${requestScope.DTO.fateRequirementFile}</li>
                        <c:url value="SEARCH_USER_DETAIL" var="directorDetailLink">
                            <c:param name="txtUserID" value="${requestScope.DTO.fateDirectorID}"/>
                            <c:param name="txtSearch" value="${param.txtSearch}"/>
                            <c:param name="cbSearchType" value="${param.cbSearchType}"/>
                        </c:url>
                    <li class="list-group-item">Director: <a href="${pageScope.directorDetailLink}">${requestScope.DTO.fateDirectorID}</a></li>
                </ul>
                <div class="row my-3">
                    <form class="col-md-6" action="EDIT_FATE" method="POST">
                        <input type="hidden" name="txtFateID" value="${requestScope.DTO.fateID}"/>
                        <button class="btn btn-success btn-block" type="submit" value="Edit Fate">
                            Edit
                        </button>
                    </form>
                    <form class="col-md-6" action="DELETE_FATE" method="POST">
                        <input type="hidden" name="pageID" value="1"/>
                        <input type="hidden" name="txtFateID" value="${requestScope.DTO.fateID}"/>
                        <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                        <input type="hidden" name="cbSearchType" value="${param.cbSearchType}"/>
                        <button class="btn btn-danger btn-block" type="submit" value="Delete Fate">
                            Delete
                        </button>
                    </form>
                </div>
            </div>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
