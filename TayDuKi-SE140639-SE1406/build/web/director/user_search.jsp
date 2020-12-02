<%-- 
    Document   : search
    Created on : Jun 30, 2020, 3:46:06 PM
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
            <c:if test="${requestScope.ALREADY_IN_CART != null}">
                <div class="alert alert-success text-center mx-auto block w-25" role="alert">
                    ${requestScope.ALREADY_IN_CART}
                </div>
            </c:if>
            <c:if test="${requestScope.SUCCESS != null}">
                <div class="alert alert-success text-center mx-auto block w-25" role="alert">
                    ${requestScope.SUCCESS}
                </div>
            </c:if>
            <c:if test="${requestScope.SEARCH_RESULT != null}">
                <div class="row mx-0 my-0 justify-content-center">
                    <c:if test="${not empty requestScope.SEARCH_RESULT}" var="checkUserList">
                        <c:forEach var="dto" items="${requestScope.SEARCH_RESULT}" varStatus="counter">
                            <c:url value="SEARCH_USER_DETAIL" var="userDetailLink">
                                <c:param name="txtUserID" value="${dto.userID}"/>
                                <c:param name="txtSearch" value="${param.txtSearch}"/>
                                <c:param name="cbSearchType" value="${param.cbSearchType}"/>
                            </c:url>
                            <div class="card col-sm-6 col-md-4 col-lg-3 mx-3 my-3">
                                <a class="text-center" href="${pageScope.userDetailLink}">                             
                                    <img class="img-thumbnail card-img-top" src="images/user/${dto.userImage}">
                                </a>
                                <div class="card-body text-center">
                                    <h5 class="card-title">${dto.fullname}</h5>
                                    <p class="card-text">Role: ${dto.roleID}</p>
                                    <form action="FATE_ACTOR_ADD_PAGE" method="POST">
                                        <input type="hidden" name="txtActorID" value="${dto.userID}"/>
                                        <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                        <input type="hidden" name="cbSearchType" value="${param.cbSearchType}"/>
                                        <input type="hidden" name="pageID" value="${requestScope.CURRENT_PAGE}"/>
                                        <button class="btn btn-info btn-block" value="Add to fate actor cart">
                                            Add to cart
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </c:if>
                <c:if test="${!pageScope.checkUserList}">
                    <h2 style="color: red">No record found</h2>
                </c:if>
            </div>
            <ul class="pagination justify-content-center">
                <c:forEach begin="1" end="${requestScope.SEARCH_RESULT_PAGE_COUNT}" varStatus="counter">
                    <c:url value="SEARCH_USER" var="searchPageLink">
                        <c:param name="cbSearchType" value="${param.cbSearchType}"/>
                        <c:param name="pageID" value="${counter.count}"/>
                        <c:param name="txtSearch" value="${param.txtSearch}"/>
                    </c:url>
                    <li class="page-item 
                        <c:if test="${requestScope.CURRENT_PAGE == counter.count}">
                            active
                        </c:if>">
                        <a class="page-link" href="${searchPageLink}">${counter.count}</a>
                    </li>
                </c:forEach>
            </ul>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
