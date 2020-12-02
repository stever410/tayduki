<%-- 
    Document   : login
    Created on : Jun 29, 2020, 5:39:04 PM
    Author     : ngota
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
              integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css" />
        <title>Document</title>
    </head>
    <body id="login-page">
        <div class="container">
            <div class="login-form col-md-7">
                <c:if test="${sessionScope.USER_DTO == null}" var="checkSession">
                    <form action="LOGIN" method="POST">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" id="username" name="txtUserID" value="${param.txtUserID}"/>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="txtPassword"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary btn-block" name="action" value="Login"/>
                        </div>
                        <c:if test="${requestScope.INVALID != null}">
                            <div class="alert alert-warning" role="alert">
                                ${requestScope.INVALID}
                            </div>
                        </c:if>
                        <c:if test="${requestScope.ERROR != null}">
                            <div class="alert alert-danger" role="alert">
                                ${requestScope.ERROR}
                            </div>
                        </c:if>
                        <c:if test="${requestScope.SESSION_TIMEOUT!= null}">
                            <div class="alert alert-warning" role="alert">
                                ${requestScope.SESSION_TIMEOUT}
                            </div>
                        </c:if>
                    </form>
                </c:if>
                <c:if test="${!checkSession}">
                    <div class="text-center">
                        <c:if test="${sessionScope.USER_DTO.roleID eq 'ADM'}">
                            <a href="ADM_PAGE">Back to admin homepage</a>
                        </c:if>
                        <c:if test="${sessionScope.USER_DTO.roleID eq 'DIR'}">
                            <a href="DIR_PAGE">Back to director homepage</a>
                        </c:if>
                        <c:if test="${sessionScope.USER_DTO.roleID eq 'ACT'}">
                            <a href="ACT_PAGE">Back to actor homepage</a>
                        </c:if>
                    </div>
                </c:if>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
                integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
    </body>
</html>