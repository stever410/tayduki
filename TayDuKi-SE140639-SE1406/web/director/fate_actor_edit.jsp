<%-- 
    Document   : add_fate_actor_detail
    Created on : Jul 5, 2020, 9:31:31 PM
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
        <%@include file="director_header.jsp" %>
        <main>
            <h2>Update actor ${requestScope.DTO.actorID} in cart 1</h2>
            <form class="register-form justify-content-center col-sm-5" action="UPDATE_FATE_ACTOR_CART" method="POST">
                <div class="form-group">
                    <label for="actorID">Actor id</label>
                    <input type="text" class="form-control" id="actorID" name="txtActorID" value="${requestScope.DTO.actorID}" readonly="true">
                </div>
                <div class="form-group">
                    <label for="roleName">Role name</label>
                    <input type="text" class="form-control" id="roleName" name="txtRoleName" value="${requestScope.DTO.roleName}" readonly="true">
                </div>
                <c:if test="${requestScope.INVALID.roleNameError != null}">
                    <div class="alert alert-warning mt-2" role="alert">
                        ${requestScope.INVALID.roleNameError}
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="roleDescription">Role description</label>
                    <textarea class="form-control" id="roleDescription" rows="3" name="txtRoleDescription" placeholder="Enter role description">${requestScope.DTO.roleDescription}</textarea>
                </div>
                <c:if test="${requestScope.INVALID.roleDescriptionError != null}">
                    <div class="alert alert-warning mt-2" role="alert">
                        ${requestScope.INVALID.roleDescriptionError}
                    </div>
                </c:if>
                <button class="btn btn-primary btn-block mb-5" type="submit" name="action" value="Update actor in cart">
                    Update
                </button>
                <input type="hidden" name="txtTypeCart" value="FATE_ACTOR_CART_PAGE"/>
                <input type="hidden" value="${sessionScope.USER_DTO.userID}" name="txtDirectorID"/>
                <input type="hidden" name="cbSearchType" value="${param.cbSearchType}"/>
                <input type="hidden" name="txtSearch" value="${param.txtSearch}">
                <input type="hidden" name="pageID" value="${param.pageID}"/>
            </form>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
