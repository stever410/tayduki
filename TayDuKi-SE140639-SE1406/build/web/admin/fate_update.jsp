<%-- 
    Document   : fate_update
    Created on : Jul 4, 2020, 9:31:50 PM
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
            <h2>Fate Update</h2>
            <form class="register-form justify-content-center col-sm-5" action="UPDATE_FATE" method="POST" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="fateID">Fate id</label>
                    <input type="text" class="form-control" id="fateID" name="txtFateID" value="${requestScope.DTO.fateID}" readonly="true"required="true">
                    <c:if test="${requestScope.INVALID.fateIDError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.fateIDError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="fateName">Fate name</label>
                    <input type="text" class="form-control" id="fateName" name="txtFateName" value="${requestScope.DTO.fateName}" required="true">
                    <c:if test="${requestScope.INVALID.fateNameError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.fateNameError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="fateDescription">Fate Description</label>
                    <textarea class="form-control" id="fateDescription" rows="3" name="txtFateDescription" placeholder="Enter fate description">${requestScope.DTO.fateDescription}</textarea>
                    <c:if test="${requestScope.INVALID.fateDescriptionError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.fateDescriptionError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="fateShootPlace">Shooting place</label>
                    <input type="text" class="form-control" id="fateShootPlace" name="txtFateShootPlace" value="${requestScope.DTO.fateShootPlace}"required="true">
                    <c:if test="${requestScope.INVALID.fateShootPlaceError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.fateShootPlaceError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="fateStartTime">Start Time</label>
                    <input type="text" 
                           class="form-control" 
                           id="fateStartTime" 
                           name="txtFateStartTime" 
                           placeholder="day/month/year - EX: 15/07/2020"
                           required="true"
                           value="<fmt:formatDate value="${requestScope.DTO.fateStartTime}" pattern = "dd/MM/yyyy"/>"/>
                    <c:if test="${requestScope.INVALID.fateStartTimeError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.fateStartTimeError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="fateEndTime">End Time<i>(must greater than start date )</i></label>
                    <input type="text" 
                           class="form-control" 
                           id="fateStartTime" 
                           name="txtFateEndTime" 
                           placeholder="day/month/year - EX: 16/07/2020"
                           required="true"
                           value="<fmt:formatDate value="${requestScope.DTO.fateEndTime}" pattern = "dd/MM/yyyy"/>"/>
                    <c:if test="${requestScope.INVALID.fateEndTimeError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.fateEndTimeError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="fateShootCount">Shoot count</label>
                    <input type="number" class="form-control" id="fateShootCount" name="txtFateShootCount" value="${requestScope.DTO.fateShootCount}"required="true">
                    <c:if test="${requestScope.INVALID.fateShootCountError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.fateShootCountError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="requirementFile">Requirement file</label>
                    <input type="hidden" name="txtPreviousFile" value="${requestScope.DTO.fateRequirementFile}"/>
                    <input class="form-control-file" type="file" class="form-control" id="requirementFile" name="txtRequirementFile">
                </div>
                <div class="form-group">
                    <label for="fateDirectorID">Choose director</label>
                    <select class="form-control mr-2" name="cbDirectors" required="true">
                        <c:forEach items="${requestScope.DIRECTOR_LIST}" var="director">
                            <option value="${director.userID}"
                                    <c:if test="${requestScope.DTO.fateDirectorID eq director.userID}">
                                        selected="true"
                                    </c:if>>${director.fullname}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${requestScope.INVALID.fateDirectorIDError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.fateDirectorIDError}
                        </div>
                    </c:if>
                </div>
                <input type="hidden" name="txtDestPage" value="FATE_UPDATE_PAGE"/>
                <button class="btn btn-primary btn-block mb-5" type="submit" name="action" value="UpdateFate">
                    Update
                </button>
            </form>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
