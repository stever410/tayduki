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
        <%@include file="director_header.jsp" %>
        <c:if test="${requestScope.UPDATE_SUCCESS != null}">
            <div class="alert alert-success text-center mx-auto block w-25 mt-5" role="alert">
                ${requestScope.UPDATE_SUCCESS}
            </div>
        </c:if>
        <main class="row">
            <div class="col-sm-12 col-md-6 mx-2 text-center">     
                <img class="img-fluid" src="images/equipment/${requestScope.DTO.equipmentImage}">
            </div>
            <div class="col-sm-12 col-md-5 mx-2 text-center">
                <h4 class="mb-3">${requestScope.DTO.equipmentID} INFO</h4>
                <ul class="list-group">
                    <li class="list-group-item">Name: ${requestScope.DTO.equipmentName}</li>
                    <li class="list-group-item">Description: ${requestScope.DTO.equipmentDescription}</li>
                    <li class="list-group-item">Amount: ${requestScope.DTO.equipmentAmount}</li>
                    <li class="list-group-item">Status: ${requestScope.DTO.active}</li>
                </ul>
                <form action="ADD_TO_FATE_EQUIPMENT_CART" method="POST">
                    <input type="hidden" name="txtEquipmentID" value="${requestScope.DTO.equipmentID}"/>
                    <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                    <input type="hidden" name="cbSearchType" value="${param.cbSearchType}"/>
                    <input type="hidden" name="pageID" value="1"/>
                    <button class="btn btn-info btn-block mt-3" value="Add to fate equipment cart">
                        Add to cart
                    </button>
                </form>
            </div>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
