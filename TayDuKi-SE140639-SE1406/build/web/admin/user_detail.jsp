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
        <%@include file="admin_header.jsp" %>
        <c:if test="${requestScope.UPDATE_SUCCESS != null}">
            <div class="alert alert-success text-center mx-auto block w-25 mt-5" role="alert">
                ${requestScope.UPDATE_SUCCESS}
            </div>
        </c:if>
        <c:if test="${requestScope.DELETE_FAIL != null}">
            <div class="alert alert-warning text-center mx-auto block w-25 mt-5" role="alert">
                ${requestScope.DELETE_FAIL}
            </div>
        </c:if>
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
                <c:if test="${requestScope.DTO.roleID ne 'ADM'}">
                    <div class="row my-3">
                        <form class="col-6" action="EDIT_USER" method="POST">
                            <input type="hidden" name="txtUserID" value="${requestScope.DTO.userID}"/>
                            <button class="btn btn-info btn-block" type="submit">Edit</button>
                        </form>
                        <form class="col-6" action="DELETE_USER" method="POST">
                            <input type="hidden" name="pageID" value="1"/>
                            <input type="hidden" name="txtUserID" value="${requestScope.DTO.userID}"/>
                            <input type="hidden" name="cbSearchType" value="${param.cbSearchType}"/>
                            <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                            <button class="btn btn-danger btn-block" type="submit">Delete</button>
                        </form>
                    </div>
                </c:if>
            </div>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
