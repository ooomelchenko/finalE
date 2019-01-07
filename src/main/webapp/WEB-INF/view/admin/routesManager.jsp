<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<html>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {

        $('.td_checkbox_tariff').change(function () {
            $(this).parent().find('.td_buttons_change').show();
        });

        $('.button_change_ok').click(function () {

            var button_change_ok = $(this);

            var tariffJson = [];

            $(this).parent().parent().find('.checkbox_tariff').each(function () {
                tariffJson.push({
                    id: $(this).val(),
                    isAvailable: $(this).is(':checked')
                });
            });

            $.ajax({
                url: "${pageContext.request.contextPath}/delivery/admin/option/edit",
                method: "POST",
                data: {
                    routeId: $(this).val(),
                    tariffArray: JSON.stringify(tariffJson)
                },

                success: function () {
                    button_change_ok.parent().hide();
                    button_change_ok.parent().next().append('<span class="badge badge-success badge-sm"><fmt:message key="badge.edit.success"/></span>');
                },
                error: function () {
                    button_change_ok.parent().next().append('<span class="badge badge-error badge-sm"><fmt:message key="badge.edit.error"/></span>');
                }
            });
        });
        
        $('.button_change_cancel').click(function () {
            $(this).parent().hide();
        })
    })
</script>

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
    <title>Manager routes</title>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/delivery"><fmt:message key="nav.home"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/delivery/admin"><fmt:message key="nav.profile"/></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/delivery/admin/routes/manager"><fmt:message key="nav.routes"/></a>
            </li>
        </ul>
        <div>
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="true"><img src="${pageContext.request.contextPath}/resources/icons/logout.png" style="width: 40px; height: 40px"></a>
                <div class="dropdown-menu" x-placement="bottom-start" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 30px, 0px);">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/delivery/profile">${sessionScope.user.login}</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/delivery/logout"><fmt:message key="logout.head"/></a>
                </div>
            </div>
        </div>

        <div style="text-align: right" id="lang-div">
            <a href="?sessionLocale=en"><img src="${pageContext.request.contextPath}/resources/icons/gb.png"><fmt:message key="lang.en"/></a>
            <br>
            <a href="?sessionLocale=ua"><img src="${pageContext.request.contextPath}/resources/icons/ua.png"><fmt:message key="lang.ua"/></a>
        </div>
    </div>
</nav>

<div class="jumbotron">
    <table class="table table-hover">
        <thead>
        <tr >
            <th scope="col" class="col-2"><fmt:message key="route.table.head.start"/></th>
            <th scope="col" class="col-2"><fmt:message key="route.table.head.end"/></th>
            <th scope="col" class="col-2"><fmt:message key="route.table.head.distance"/></th>
            <c:forEach var="tariff" items="${requestScope.tariffList}">
                <th class="col-1"><c:out value="${tariff.getName()} "/></th>
            </c:forEach>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="route" items="${requestScope.routeList}">

            <tr class="table-p">

                <td><c:out value="${route.routeStart} "/></td>
                <td><c:out value="${route.routeEnd} "/></td>
                <td><c:out value="${route.distanceKm} "/></td>
                <c:forEach var="tariff" items="${requestScope.tariffList}">
                    <td class="td_checkbox_tariff">
                    <c:if test="${route.getTariffList().contains(tariff)}">
                        <input class="checkbox_tariff" type="checkbox" checked="checked" value="${tariff.getId()}">
                    </c:if>
                    <c:if test="${!route.getTariffList().contains(tariff)}">
                        <input class="checkbox_tariff" type="checkbox" value="${tariff.getId()}">
                    </c:if>
                    </td>
                </c:forEach>
                <td class="td_buttons_change" style="display: none">
                    <button class="btn btn-sm btn-outline-success button_change_ok" value="${route.getId()}">
                        ok
                    </button>
                    <button class="btn btn-sm btn-outline-warning button_change_cancel">
                        cancel
                    </button>
                </td>
                <td>

                </td>
            </tr>

        </c:forEach>
        </tbody>

    </table>

    <div>
        <ul class="pagination">
            <li class="page-item <c:if test="${(requestScope.currentPortion-1)<1}"> <c:out value="disabled"/></c:if>" >
                <a class="page-link" href="?portion=<c:out value="${requestScope.currentPortion-1}"/>">&laquo;</a>
            </li>
            <c:forEach begin="1" end="${requestScope.countOfPortions}" var="i" step="1">
                <c:choose>
                    <c:when test="${i!=requestScope.currentPortion}">
                        <li class="page-item">
                            <a class="page-link" href="?portion=<c:out value="${i}"/>"><c:out value="${i}"/></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item active">
                            <a class="page-link"><c:out value="${i}"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <li class="page-item <c:if test="${requestScope.currentPortion+1>requestScope.countOfPortions}"> <c:out value="disabled"/></c:if>" >
                <a class="page-link" href="?portion=<c:out value="${requestScope.currentPortion+1}"/>">&raquo;</a>
            </li>
        </ul>
    </div>

</div>

</body>
</html>
