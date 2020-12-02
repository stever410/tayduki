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
                <h4 class="mb-3">Fate Equipment Cart</h4>
                <c:if test="${requestScope.ERROR != null}">
                    <div class="alert alert-warning mt-2" role="alert">
                        ${requestScope.ERROR}
                    </div>
                </c:if>
                <c:if test="${requestScope.OUT_OF_STOCK != null}">
                    <div class="alert alert-warning text-center mx-auto block" role="alert">
                        ${requestScope.OUT_OF_STOCK}
                    </div>
                </c:if>
                <c:if test="${requestScope.INVALID != null}">
                    <div class="alert alert-warning text-center mx-auto block" role="alert">
                        ${requestScope.INVALID}
                    </div>
                </c:if>
                <c:if test="${sessionScope.FATE_EQUIPMENT_CART != null}" var="checkFateEquipmentCart">
                    <table class="table" border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Equipment id</th>
                                <th>Amount</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.FATE_EQUIPMENT_CART.cart}" var="cart" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${cart.key.equipmentID}</td>
                                    <td>
                                        <form action="UPDATE_FATE_EQUIPMENT_CART" method="POST">
                                            <input type="number" name="txtEquipmentAmount" value="${cart.value}" required="true" min="1"/>
                                            <input type="hidden" name="pageID" value="1"/>
                                            <input type="hidden" value="${sessionScope.USER_DTO.userID}" name="txtDirectorID"/>
                                            <input type="hidden" value="${cart.key.equipmentID}" name="txtEquipmentID"/>
                                            <input type="hidden" value="${param.txtSearch}" name="txtSearch"/>
                                            <input type="hidden" value="${param.cbSearchType}" name="cbSearchType"/>
                                            <input type="hidden" name="txtTypeCart" value="FATE_EQUIPMENT_CART_PAGE"/>
                                            <button class="btn btn-info" type="submit" value="Update Equipment Amount">Update</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="DELETE_FATE_EQUIPMENT_CART" method="POST">
                                            <input type="hidden" name="pageID" value="1"/>
                                            <input type="hidden" value="${sessionScope.USER_DTO.userID}" name="txtDirectorID"/>
                                            <input type="hidden" value="${cart.key.equipmentID}" name="txtEquipmentID"/>
                                            <input type="hidden" value="${param.txtSearch}" name="txtSearch"/>
                                            <input type="hidden" value="${param.cbSearchType}" name="cbSearchType"/>
                                            <input type="hidden" name="txtTypeCart" value="FATE_EQUIPMENT_CART_PAGE"/>
                                            <button class="btn btn-danger" type="submit" value="Delete Fate Equipment">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <form action="REGISTER_FATE_EQUIPMENT_CART" method="POST">
                        <input type="hidden" name="txtTypeCart" value="FATE_EQUIPMENT_CART_PAGE"/>
                        <input type="hidden" name="pageID" value="1"/>
                        <input type="hidden" value="${param.txtSearch}" name="txtSearch"/>
                        <input type="hidden" value="${param.cbSearchType}" name="cbSearchType"/>
                        <input type="hidden" value="${sessionScope.USER_DTO.userID}" name="txtDirectorID"/>
                        <select class="form-control my-3" name="cbDirectorFate" required="true">
                            <c:forEach items="${requestScope.LIST_FATE_OF_DIRECTOR}" var="fate">
                                <option value="${fate.fateID}">${fate.fateID} - ${fate.fateName}</option>
                            </c:forEach>
                        </select>
                        <button type="submit" class="btn btn-primary">Submit cart</button>
                    </form>
                </c:if>
                <c:if test="${!checkFateEquipmentCart}">
                    <font color="red">
                    No items in cart yet
                    </font>
                </c:if>
            </div>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
