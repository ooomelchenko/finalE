<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<base href="${pageContext.request.contextPath}/"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Doctor App</title>
</head>
    <body>
        <h2>
            Hello WEB! <br/>
        </h2>

        <br/>

        <br>
        <form method="GET" action="login">
            <input type="text" placeholder="content.input.login" name="login"/><br>
            <input type="password" placeholder="content.input.password" name = "password"/><br>
            <input type="text" placeholder="content.input.firstname" name = "firstname"/><br>
            <input type="text" placeholder="content.input.lastname" name = "lastname"/><br>
            <input type="email" placeholder="content.input.email" name = "email"/><br>

            <input type="submit" value="content.input.send" />
        </form>

    </body>
</html>