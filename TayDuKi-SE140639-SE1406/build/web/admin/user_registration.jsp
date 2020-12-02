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
            <h2>User Registration</h2>
            <form class="register-form justify-content-center col-sm-5" action="REGISTER_USER" method="POST" enctype="multipart/form-data">
                <c:if test="${requestScope.REGISTRATION_SUCCESS != null}">
                    <div class="alert alert-success mt-2" role="alert">
                        ${requestScope.REGISTRATION_SUCCESS}
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" id="username" name="txtUserID" value="${requestScope.DTO.userID}"required="true">
                    <c:if test="${requestScope.INVALID.userIDError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.userIDError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label class="mr-2" for="role">Role</label><br />
                    <select class="form-control w-100" id="role" name="cbRoles" required="true">
                        <option value="Actor">Actor</option>
                        <option value="Director">Director</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="fullname">Fullname</label>
                    <input type="text" class="form-control" id="fullname" name="txtFullname" value="${requestScope.DTO.fullname}" required="true">
                    <c:if test="${requestScope.INVALID.fullnameError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.fullnameError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label class="mr-4" for="gender">Gender</label>
                    <input type="radio" id="male" name="rdGender" value="Male" 
                           <c:if test="${requestScope.DTO.sex == 'M'.charAt(0)}">
                               checked="true"
                           </c:if> required="true">
                    <label class="mr-4" for="male">Male</label>
                    <input type="radio" id="female" name="rdGender" value="Female" 
                           <c:if test="${requestScope.DTO.sex == 'F'.charAt(0)}">
                               checked="true"
                           </c:if>>
                    <label class="mr-4" for="female">Female</label>
                    <input type="radio" id="other" name="rdGender" value="Other" 
                           <c:if test="${requestScope.DTO.sex == 'O'.charAt(0)}">
                               checked="true"
                           </c:if>>
                    <label for="other">Other</label>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="txtPassword" required="true">
                    <c:if test="${requestScope.INVALID.passwordError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.passwordError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="confirm">Confirm password</label>
                    <input type="password" class="form-control" id="confirm" name="txtConfirm" required="true">
                    <c:if test="${requestScope.INVALID.confirmError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.confirmError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="phoneNum">Phone number</label>
                    <input type="text" class="form-control" id="phoneNum" name="txtPhoneNum" value="${requestScope.DTO.phoneNum}"required="true">
                    <c:if test="${requestScope.INVALID.phoneNumError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.phoneNumError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="email">Email (Ex: abc@gmail.com.vn)</label>
                    <input type="email" class="form-control" id="email" name="txtEmail" value="${requestScope.DTO.email}" required="true">
                    <c:if test="${requestScope.INVALID.emailError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.emailError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control" id="description" rows="3" name="txtDescription" placeholder="Enter user description">${requestScope.DTO.userDescription}</textarea>
                    <c:if test="${requestScope.INVALID.descriptionError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.descriptionError}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="image">Image</label>
                    <input class="form-control-file" type="file" class="form-control" id="image" name="txtImage" accept="image/*">
                    <c:if test="${requestScope.INVALID.imgError != null}">
                        <div class="alert alert-warning mt-2" role="alert">
                            ${requestScope.INVALID.imgError}
                        </div>
                    </c:if>
                </div>
                <button class="btn btn-primary btn-block mb-5" type="submit" name="action" value="RegisterUser">
                    Register
                </button>
            </form>
        </main>
        <%@include file="../footer.html" %>
    </body>
</html>
