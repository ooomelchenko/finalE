<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<html>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>
<style>
    body#LoginForm{
        background: url("https://hdwallsource.com/img/2014/9/blur-26347-27038-hd-wallpapers.jpg") no-repeat center;
        background-size:cover; padding:10px;}

    .panel h2{ color:#444444; font-size:18px; margin:0 0 8px 0;}
    .panel p { color:#777777; font-size:14px; margin-bottom:30px; line-height:24px;}

    .main-div {
        background: #ffffff none repeat scroll 0 0;
        border-radius: 2px;
        margin: 10px auto 30px;
        max-width: 38%;
        padding: 50px 70px 70px 71px;
    }

    .login-form{ text-align:center;}
    .forgot a {
        color: #777777;
        font-size: 14px;
        text-decoration: underline;
    }

    .back a {color: #444444; font-size: 13px;text-decoration: none;}

    #lang-div img{
        width: 10px;
        height: 10px;
        opacity: 0.7;
    }
    #lang-div img:hover{
        opacity: 1;
    }

</style>

<head>
    <title>login</title>
</head>

<body id="LoginForm">
<div class="container">

    <div class="login-form">

        <div class="main-div">
            <div style="text-align: right" id="lang-div">
                <a href="?sessionLocale=en"><img src="${pageContext.request.contextPath}/resources/icons/gb.png"><fmt:message key="lang.en"/></a>
                <br>
                <a href="?sessionLocale=ua"><img src="${pageContext.request.contextPath}/resources/icons/ua.png"><fmt:message key="lang.ua"/></a>
            </div>
            <div class="panel">
                <h2><fmt:message key="input.login"/></h2>
                <p><fmt:message key="login.hello"/></p>
            </div>

            <form id="Login" method="POST" action="${pageContext.request.contextPath}/delivery/login">

                <div class="form-group">
                    <input type="text" class="form-control" name="login" placeholder="<fmt:message key="input.login"/>">
                </div>

                <div class="form-group">
                    <input type="password" class="form-control" name="password" placeholder="<fmt:message key="input.password"/>">
                </div>

                <button type="submit" class="btn btn-primary"><fmt:message key="input.send"/></button>

            </form>

            <p class="text-danger"> <c:out value="${requestScope.resultMessage}"/></p>
            <a href="${pageContext.request.contextPath}/delivery/registration" style=" text-align: right">
                <img src="${pageContext.request.contextPath}/resources/icons/registration.png" style="width: 30px; height: 30px">
                <fmt:message key="registration.head"/>
            </a>

        </div>

    </div>
</div>
</body>
</html>
