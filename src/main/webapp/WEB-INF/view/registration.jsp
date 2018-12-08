<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<html lang="${sessionScope.lang}">
<link href="resources/css/generalStyle.css" rel="stylesheet" type="text/css">
<head>
    <title>Registration</title>
</head>
<body>
<header>
    <div class="div_lang" style=" width: 30%">
        <a href="?sessionLocale=en"><img src="resources/icons/gb.png"><fmt:message key="lang.en"/></a>
        <br/>
        <a href="?sessionLocale=ua"><img src="icons/ua.png"><fmt:message key="lang.ua"/></a>
    </div>
</header>

<form method="POST">

    <input type="text" placeholder="<fmt:message key="input.login"/>" name="login" value="${requestScope.userDTO.getLogin()}"/>
    <b>${requestScope.wrong_login}</b><br>

    <input type="password" placeholder="<fmt:message key="input.password"/>" name = "password" value="${requestScope.userDTO.getPassword()}"/>
    <b>${requestScope.wrong_password}</b><br>

    <input type="text" placeholder="<fmt:message key="input.firstname"/>" name = "firstname" value="${requestScope.userDTO.getFirstName()}"/>
    <b>${requestScope.wrong_firstname}</b><br>

    <input type="text" placeholder="<fmt:message key="input.lastname"/>" name = "lastname" value="${requestScope.userDTO.getLastName()}"/>
    <b>${requestScope.wrong_lastname}</b><br>

    <input type="email" placeholder="<fmt:message key="input.email"/>" name = "email" value="${requestScope.userDTO.getEmail()}"/>
    <b>${requestScope.wrong_email}</b><br>

        <select name="role">
            <c:forEach var="role" items="${enumRoles}">
                <option value="${role.name()}"  <c:if test="${role.name()}=${requestScope.userDTO.getRole().name()}">selected="selected"</c:if> >
                    <c:out value="${role.name()}"/>
                </option>
            </c:forEach>
        </select>
    <br>
    <input type="submit" value="<fmt:message key="input.send"/>" />
</form>

</body>
</html>