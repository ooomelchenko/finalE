<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<base href="${pageContext.request.contextPath}/"/>

<html>
<head>
    <title>Registration</title>
</head>
<body>

<form method="GET" action="registration">

    <input type="text" placeholder="<fmt:message key="input.login"/>" name="login" value="${requestScope.userDTO.getLogin()}"/><br>

    <input type="password" placeholder="<fmt:message key="input.password"/>" name = "password" value="${requestScope.userDTO.getPassword()}"/><br>

    <input type="text" placeholder="<fmt:message key="input.firstname"/>" name = "firstname" value="${requestScope.userDTO.getFirstName()}"/><br>

    <input type="text" placeholder="<fmt:message key="input.lastname"/>" name = "lastname" value="${requestScope.userDTO.getLastName()}"/><br>

    <input type="email" placeholder="<fmt:message key="input.email"/>" name = "email" value="${requestScope.userDTO.getEmail()}"/><br>

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