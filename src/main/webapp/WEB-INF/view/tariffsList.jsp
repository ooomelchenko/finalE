<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<html>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<style>
    #lang-div img {
        width: 20px;
        height: 20px;
        opacity: 0.7;
    }

    #lang-div img:hover {
        opacity: 1;
    }
</style>

<head>
    <title>Tariffs</title>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/"><fmt:message key="nav.home"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/delivery/routes"><fmt:message key="nav.routes"/></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/delivery/tariffs"><fmt:message key="nav.tariffs"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/delivery/calculator"><fmt:message key="nav.calculator"/></a>
            </li>
        </ul>
        <div>

            <c:if test="${sessionScope.user == null}">
                <a href="${pageContext.request.contextPath}/delivery/login">
                    <fmt:message key="input.login"/> <img src="${pageContext.request.contextPath}/resources/icons/login.png" style="width: 40px; height: 40px">
                </a>

            </c:if>
            <c:if test="${sessionScope.user != null}">
                <div class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="true"><img src="${pageContext.request.contextPath}/resources/icons/logout.png" style="width: 40px; height: 40px"></a>
                    <div class="dropdown-menu" x-placement="bottom-start" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 30px, 0px);">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/delivery/profile">${sessionScope.user.login}</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/delivery/logout"><fmt:message key="logout.head"/></a>
                    </div>
                </div>
            </c:if>

        </div>

        <div style="text-align: right" id="lang-div">
            <a href="?sessionLocale=en"><img src="${pageContext.request.contextPath}/resources/icons/gb.png"><fmt:message key="lang.en"/></a>
            <br>
            <a href="?sessionLocale=ua"><img src="${pageContext.request.contextPath}/resources/icons/ua.png"><fmt:message key="lang.ua"/></a>
        </div>
    </div>
</nav>

<div class="jumbotron">

    <table class="table table-hover col-md-8">
        <thead>
        <tr>
            <th scope="col" class="col-4"><fmt:message key="tariff.table.head.name"/></th>
            <th scope="col" class="col-4"><fmt:message key="tariff.table.head.costPerKm"/></th>
            <th scope="col" class="col-4"><fmt:message key="tariff.table.head.costPerKg"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tariff" items="${sessionScope.tariffList}">
            <tr class="table-p">
                <td><c:out value="${tariff.name} "/></td>
                <td><c:out value="${tariff.costPerKm/100} "/></td>
                <td><c:out value="${tariff.costPerKg/100} "/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>

</html>
