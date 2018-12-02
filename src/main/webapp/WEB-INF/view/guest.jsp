<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<base href="${pageContext.request.contextPath}/"/>
<%!
    String getFormattedDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        return sdf.format(new Date());
    }
%>
<html>
<head>
    <title>Guest</title>
</head>

<body>
<header>
    <div>
        <a href="login">Login</a>
    </div>
    <div>
        <a href="Registration">Registration</a>
    </div>
    <div>
        <h2>
            Hello Guest! <br/>
            <i>Сегодня <%= getFormattedDate() %></i>
        </h2>
    </div>
</header>

<a href="exception">Exception</a>
<footer>

</footer>
</body>

</html>
