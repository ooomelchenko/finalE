<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tarrifs List</title>
</head>
    <body>
        <h2>
            List Students <br/>
        </h2>
        <table>
        <tr><th>Name</th><th>Group</th></tr>
        <c:forEach var="i" items="${orders}">
            <tr><td>${i.name}<c:out value="${i.name}"/></td><td>${i.groupe}</td>
        </c:forEach>
        </table>
        <br>
        <br>
        <%=request.getAttribute("orders")%>
        <br>
        <c:out value="${orders}"/>



        <br/>
        <a href="index.jsp">index</a>
        <br/>
        <a href="${pageContext.request.contextPath}/WEB-INF/index.jsp">index</a>
    </body>
</html>
