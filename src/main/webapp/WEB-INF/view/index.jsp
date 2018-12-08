<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<html>
<link href="../../resources/css/generalStyle.css" rel="stylesheet" type="text/css">

<head>
    <title>Delivery</title>
</head>

<body>
<header>
    <div>
        <h2>
            Hello Guest! <br/>
        </h2>
    </div>
    <div>
        <a href="login">Login</a>
        <br>
        <a href="registration">Registration</a>
        <br>
        <a href="logout">Logout</a>
    </div>
    <div class="div_lang" style=" width: 30%">
        <a href="?sessionLocale=en"><img src="../../resources/icons/gb.png"><fmt:message key="lang.en"/></a>
        <br/>
        <a href="?sessionLocale=ua"><img src="../../resources/icons/ua.png"><fmt:message key="lang.ua"/></a>
    </div>

</header>
<div>

</div>
<footer>

</footer>
</body>

</html>
