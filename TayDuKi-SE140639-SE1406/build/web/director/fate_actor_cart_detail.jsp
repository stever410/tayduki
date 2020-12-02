<%-- 
    Document   : fate_actor_cart_detail
    Created on : Jul 7, 2020, 9:17:02 PM
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
        <main class="row justify-content-center">
            <div class="text-center">
                <h4 class="mb-3">Fate Actor Cart</h4>
                <c:if test="${requestScope.ERROR != null}">
                    <div class="alert alert-warning mt-2" role="alert">
                        ${requestScope.ERROR}
                    </div>
                </c:if>
                <c:if test="${sessionScope.FATE_ACTOR_CART!= null}" var="checkFateActorCart">
                    <table class="table" border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Role name</th>
                                <th>Actor id</th>
                                <th>Role description</th>
                                <th>Delete</th>
                                <th>Edit</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.FATE_ACTOR_CART.cart}" var="cart" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${cart.value.roleName}</td>
                                    <td>${cart.value.actorID}</td>
                                    <td>${cart.value.roleDescription}</td>
                                    <td>
                                        <form action="DELETE_FATE_ACTOR_CART" method="POST">
                                            <input type="hidden" name="pageID" value="1"/>
                                            <input type="hidden" value="${sessionScope.USER_DTO.userID}" name="txtDirectorID"/>
                                            <input type="hidden" value="${cart.value.roleName}" name="txtRoleName"/>
                                            <input type="hidden" value="${param.txtSearch}" name="txtSearch"/>
                                            <input type="hidden" value="${param.cbSearchType}" name="cbSearchType"/>
                                            <input type="hidden" name="txtTypeCart" value="FATE_ACTOR_CART_PAGE"/>
                                            <button class="btn btn-danger" type="submit" value="Delete Fate Actor">Delete</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="EDIT_FATE_ACTOR_CART" method="POST">
                                            <input type="hidden" name="pageID" value="1"/>
                                            <input type="hidden" value="${sessionScope.USER_DTO.userID}" name="txtDirectorID"/>
                                            <input type="hidden" value="${cart.value.roleName}" name="txtRoleName"/>
                                            <input type="hidden" value="${param.txtSearch}" name="txtSearch"/>
                                            <input type="hidden" value="${param.cbSearchType}" name="cbSearchType"/>
                                            <input type="hidden" name="txtTypeCart" value="FATE_ACTOR_CART_PAGE"/>
                                            <button class="btn btn-info" type="submit" value="Edit Fate Actor">Edit</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <form action="REGISTER_FATE_ACTOR_CART" method="POST">
                        <input type="hidden" name="pageID" value="1"/>
                        <input type="hidden" value="${param.txtSearch}" name="txtSearch"/>
                        <input type="hidden" value="${param.cbSearchType}" name="cbSearchType"/>
                        <input type="hidden" value="${sessionScope.USER_DTO.userID}" name="txtDirectorID"/>
                        <input type="hidden" name="txtTypeCart" value="FATE_ACTOR_CART_PAGE"/>
                        <select class="form-control my-3" name="cbDirectorFate" required="true">
                            <c:forEach items="${requestScope.LIST_FATE_OF_DIRECTOR}" var="fate">
                                <option value="${fate.fateID}">${fate.fateID} - ${fate.fateName}</option>
                            </c:forEach>
                        </select>
                        <button type="submit" class="btn btn-primary">Submit cart</button>
                    </form>
                </c:if>
                <c:if test="${!checkFateActorCart}">
                    <font color="red">
                    No items in cart yet
                    </font>
                </c:if>
            </div>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
