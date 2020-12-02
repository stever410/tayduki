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
                <c:url var="homepageLink" value="ACT_PAGE"/>
                <a class="navbar-brand" href="${homepageLink}">TayDuKy</a>
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
                            <c:url value="LOAD_FATE_HISTORY" var="fateHistoryLink">
                                <c:param name="pageID" value="1"/>
                            </c:url>
                            <a class="nav-link" href="${pageScope.fateHistoryLink}">Fate History</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="LOAD_INCOMING_FATE">Incoming Fates</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="LOAD_ACTOR_FATE">Download fates</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="LOAD_NOTIFICATION">Notifications</a>
                        </li>
                    </ul>
                    <img class="user-icon"
                         src="images/user/${sessionScope.USER_DTO.userImage}">
                    <li class="nav-item dropdown user-dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">${sessionScope.USER_DTO.fullname}</a>
                        <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                            <c:url value="SEARCH_USER_DETAIL" var="USER_INFO_LINK">
                                <c:param name="txtUserID" value="${sessionScope.USER_DTO.userID}"/>
                            </c:url>
                            <a class="dropdown-item" href="${pageScope.USER_INFO_LINK}">Info</a>
                            <c:url var="homepage" value="LOGOUT"/>
                            <a class="dropdown-item" href="${homepage}">Logout</a>
                        </div>
                    </li>
                </div>
            </nav>
