<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
