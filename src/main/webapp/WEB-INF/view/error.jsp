<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<html>

<head>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>
    <script>
        $(document).ready(function () {
            $('#a_show_trace').click(function () {
                $('#p_trace').show();
            })
        })
    </script>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
          type="text/css">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="page.error"/></title>
</head>
<body>
<div class="container">

    <div class="alert alert-dismissible alert-danger">
        <h2 class="text-warning"><fmt:message key="page.error.head"/></h2>
    </div>

    <div class="jumbotron">
        <div class="alert alert-dismissible alert-warning">
           <a id="a_show_trace">
               <h2><fmt:message key="page.error"/>: <%= exception.getMessage() %></h2>
           </a>
            <p id="p_trace" style="display: none">
                <%=exception%>
            </p>
        </div>
        <p class="lead">
            <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/delivery/" type="button"><fmt:message key="nav.home"/></a>
        </p>
    </div>

</div>
</body>
</html>
