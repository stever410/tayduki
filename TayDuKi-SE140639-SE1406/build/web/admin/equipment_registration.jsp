<%-- 
    Document   : actor-registration
    Created on : Jun 30, 2020, 11:07:25 AM
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
        <main>
            <h2>Equipment Registration</h2>
            <form class="register-form justify-content-center col-sm-5" action="REGISTER_EQUIPMENT" method="POST" enctype="multipart/form-data">
                <c:if test="${requestScope.REGISTRATION_SUCCESS != null}">
                    <div class="alert alert-success mt-2" role="alert">
                        ${requestScope.REGISTRATION_SUCCESS}
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="equipmentID">Equipment id</label>
                    <input type="text" class="form-control" id="equipmentID" name="txtEquipmentID" value="${requestScope.DTO.equipmentID}" required="true">
                    <c:if test="${requestScope.INVALID.equipmentIDError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.equipmentIDError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="equipmentName">Equipment name</label>
                    <input type="text" class="form-control" id="equipmentName" name="txtEquipmentName" value="${requestScope.DTO.equipmentName}" required="true">
                    <c:if test="${requestScope.INVALID.equipmentNameError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.equipmentNameError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="equipmentDescription">Description</label>
                    <textarea class="form-control" id="equipmentDescription" rows="3" name="txtEquipmentDescription" placeholder="Enter equipment description">${requestScope.DTO.equipmentDescription}</textarea>
                    <c:if test="${requestScope.INVALID.equipmentDescriptionError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.equipmentDescriptionError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="image">Image</label>
                    <input class="form-control-file" type="file" class="form-control" id="image" name="txtEquipmentImage" accept="image/*">
                    <c:if test="${requestScope.INVALID.equipmentImageError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.equipmentImageError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="equipmentAmount">Amount</label>
                    <input type="number" class="form-control" id="equipmentAmount" name="txtEquipmentAmount" value="${requestScope.DTO.equipmentAmount}">
                    <c:if test="${requestScope.INVALID.equipmentAmountError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.equipmentAmountError}
                        </div>
                    </c:if>
                </div>
                <button class="btn btn-primary btn-block mb-5" type="submit" name="action" value="RegisterEquipment">
                    Register
                </button>
            </form>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
