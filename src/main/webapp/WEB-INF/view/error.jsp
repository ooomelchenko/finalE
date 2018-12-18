<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.bundle.min.js"
            type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
          type="text/css">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error</title>
</head>
<body>
<div class="container">

    <div class="alert alert-dismissible alert-danger">
        <h2 class="text-danger">Error Page</h2>
    </div>

    <div class="jumbotron">
        <div class="alert alert-dismissible alert-warning">
            <h2>Error <%= exception %></h2>
        </div>
    </div>

</div>
</body>
</html>
