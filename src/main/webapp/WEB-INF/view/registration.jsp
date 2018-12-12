<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<html lang="${sessionScope.lang}">

<head>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <style>
        body#RegistrationForm {
            background: url("https://hdwallsource.com/img/2014/9/blur-26347-27038-hd-wallpapers.jpg") no-repeat center;
            background-size: cover;
            padding: 10px;
        }

        .panel h2 {
            color: #444444;
            font-size: 18px;
            margin: 0 0 8px 0;
        }

        .panel p {
            color: #777777;
            font-size: 14px;
            margin-bottom: 30px;
            line-height: 24px;
        }

        .main-div {
            background: #ffffff none repeat scroll 0 0;
            border-radius: 2px;
            margin: 10px auto 30px;
            max-width: 38%;
            padding: 50px 70px 70px 71px;
        }

        .login-form {
            text-align: center;
        }

        .forgot a {
            color: #777777;
            font-size: 14px;
            text-decoration: underline;
        }

        .back a {
            color: #444444;
            font-size: 13px;
            text-decoration: none;
        }

        #lang-div img {
            width: 10px;
            height: 10px;
            opacity: 0.7;
        }

        #lang-div img:hover {
            opacity: 1;
        }

    </style>

    <title>Registration</title>
</head>

<body id="RegistrationForm">

<div class="container">

    <div class="login-form">

        <div class="main-div">
            <div style="text-align: right" id="lang-div">
                <a href="?sessionLocale=en"><img src="${pageContext.request.contextPath}/resources/icons/gb.png"><fmt:message key="lang.en"/></a>
                <br>
                <a href="?sessionLocale=ua"><img src="${pageContext.request.contextPath}/resources/icons/ua.png"><fmt:message key="lang.ua"/></a>
            </div>

            <div class="panel">
                <h2><fmt:message key="registration.head"/></h2>
                <p><fmt:message key="registration.hello"/></p>
            </div>

            <form method="POST">

                <div class="form-group">
                    <label style="width: 100%">
                        <select name="role">
                            <c:forEach var="role" items="${enumRoles}">
                                <option value="${role.name()}"
                                        <c:if test="${role.name()}=${requestScope.userDTO.getRole().name()}">selected="selected"</c:if> >
                                    <c:out value="${role.name()}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </label>
                </div>

                <div class="form-group">
                    <input class="form-control" name="login" type="text" value="${requestScope.userDTO.getLogin()}"
                           placeholder="<fmt:message key="input.login"/>"/>
                    <p class="text-danger">${requestScope.wrong_login}</p>
                </div>

                <div class="form-group">
                    <input class="form-control" name="password" type="password"
                           value="${requestScope.userDTO.getPassword()}"
                           placeholder="<fmt:message key="input.password"/>"/>
                    <p class="text-danger">${requestScope.wrong_password}</p>
                </div>

                <div class="form-group">
                    <input class="form-control" name="firstname" type="text"
                           value="${requestScope.userDTO.getFirstName()}"
                           placeholder="<fmt:message key="input.firstname"/>"/>
                    <p class="text-danger">${requestScope.wrong_firstname}</p>
                </div>

                <div class="form-group">
                    <input class="form-control" name="lastname" type="text"
                           value="${requestScope.userDTO.getLastName()}"
                           placeholder="<fmt:message key="input.lastname"/>"/>
                    <p class="text-danger">${requestScope.wrong_lastname}</p>
                </div>

                <div class="form-group">
                    <input class="form-control" name="email" type="email"
                           value="${requestScope.userDTO.getEmail()}"
                           placeholder="<fmt:message key="input.email"/>"
                           />
                    <p class="text-danger">${requestScope.wrong_email}</p>
                </div>

                <button type="submit" class="btn btn-primary"><fmt:message key="input.send"/></button>

            </form>

        </div>

    </div>
</div>

</body>
</html>