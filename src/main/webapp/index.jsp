<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<html>
<link href="${pageContext.request.contextPath}/resources/css/generalStyle.css" rel="stylesheet" type="text/css">

<head>
    <title>Delivery</title>
</head>

<body>
<header>
    <div>
        <a href="${pageContext.request.contextPath}/delivery/login">Login</a>
        <br>
        <a href="${pageContext.request.contextPath}/delivery/registration">Registration</a>
        <br>
        <a href="${pageContext.request.contextPath}/delivery/logout">Logout</a>
    </div>
    <div>
        <h2>
            Hello Guest!
        </h2>
    </div>
    <div class="div_lang" style=" width: 30%">
        <a href="?sessionLocale=en"><img src="${pageContext.request.contextPath}/resources/icons/gb.png"><fmt:message key="lang.en"/></a>
        <br/>
        <a href="?sessionLocale=ua"><img src="${pageContext.request.contextPath}/resources/icons/ua.png"><fmt:message key="lang.ua"/></a>
    </div>

</header>
<div>

</div>
<footer>

</footer>
</body>

</html>
