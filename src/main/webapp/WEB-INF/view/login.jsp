<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<base href="${pageContext.request.contextPath}/"/>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<html>
<head>
    <title>login</title>
</head>
<body>
<header>
    <h2>
        Hello !
    </h2>
</header>
<br>

<c:out value="${requestScope.message} "/>
<form method="GET" action="login">
    <input type="text" placeholder="content.input.login" name="login"/><br>
    <input type="password" placeholder="content.input.password" name="password"/><br>
    <input type="submit" value="content.input.send"/>
</form>

</body>
</html>