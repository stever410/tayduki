<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <body>
        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <c:url value="DIR_PAGE" var="homepageLink">
                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                    <c:param name="cbSearchType" value="${param.cbSearchType}"/>
                </c:url>
                <a class="navbar-brand" href="${pageScope.homepageLink}">TayDuKy</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
                        aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                        <li class="nav-item active">
                            <a class="nav-link" href="${pageScope.homepageLink}">Home</a>
                        </li>
                        <li class="nav-item active">
                            <c:url value="LOAD_NOTIFICATION" var="notificationLink">
                                <c:param name="txtSearch" value="${param.txtSearch}"/>
                                <c:param name="cbSearchType" value="${param.cbSearchType}"/>
                            </c:url>
                            <a class="nav-link" href="${pageScope.notificationLink}">Notifications</a>
                        </li>
                    </ul>
                    <form class="form-inline mx-auto" action="SEARCH" method="POST">
                        <input type="hidden" name="pageID" value="1"/>
                        <select class="form-control mr-2" id="role" name="cbSearchType">
                            <option value="User"
                                    <c:if test="${param.cbSearchType eq 'User'}">
                                        selected="true"
                                    </c:if>>Actor</option>
                            <option value="Equipment"                                     
                                    <c:if test="${param.cbSearchType eq 'Equipment'}">
                                        selected="true"
                                    </c:if>>Equipment</option>
                        </select>
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" name="txtSearch" value="${param.txtSearch}"/>
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="action">Search</button>
                    </form>
                    <c:url var="fateActorCartLink" value="LOAD_DIRECTOR_FATE">
                        <c:param name="txtDirectorID" value="${sessionScope.USER_DTO.userID}"/>
                        <c:param name="txtTypeCart" value="FATE_ACTOR_CART_PAGE"/>
                    </c:url>
                    <c:url var="fateEquipmentCartLink" value="LOAD_DIRECTOR_FATE">
                        <c:param name="txtDirectorID" value="${sessionScope.USER_DTO.userID}"/>
                        <c:param name="txtTypeCart" value="FATE_EQUIPMENT_CART_PAGE"/>
                    </c:url>
                    <a class="cart mr-5" href="${pageScope.fateActorCartLink}">
                        Cart 1
                        <img class="mr-2" src="https://img.icons8.com/windows/32/000000/shopping-cart.png"/>
                    </a>
                    <a class="cart mr-5" href="${pageScope.fateEquipmentCartLink}">
                        Cart 2
                        <img class="mr-2" src="https://img.icons8.com/windows/32/000000/shopping-cart.png"/>
                    </a>
                    <img class="user-icon"
                         src="images/user/${sessionScope.USER_DTO.userImage}">
                    <li class="nav-item dropdown user-dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">${sessionScope.USER_DTO.fullname}</a>
                        <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                            <c:url value="SEARCH_USER_DETAIL" var="USER_INFO_LINK">
                                <c:param name="txtUserID" value="${sessionScope.USER_DTO.userID}"/>
                                <c:param name="txtSearch" value="${param.txtSearch}"/>
                                <c:param name="cbSearchType" value="${param.cbSearchType}"/>
                            </c:url>
                            <a class="dropdown-item" href="${pageScope.USER_INFO_LINK}">Info</a>
                            <c:url var="homepage" value="LOGOUT"/>
                            <a class="dropdown-item" href="${homepage}">Logout</a>
                        </div>
                    </li>
                </div>
            </nav>
