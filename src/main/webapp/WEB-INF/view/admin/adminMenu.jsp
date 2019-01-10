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

        $('.button_change_ok').click(function () {
            var tr_tariff_data= $(this).parent().parent().find('.tariff_data');
            $.ajax({
                url: "${pageContext.request.contextPath}/delivery/admin/tariff/edit",
                method: "POST",
                data: {tariffId: $(this).val(),
                    costPerKm: (tr_tariff_data.find('.costPerKm').val()*100).toFixed(0),
                    costPerKg: (tr_tariff_data.find('.costPerKg').val()*100).toFixed(0),
                    paceDayKm: tr_tariff_data.find('.paceDayKm').val()
                },

                success: function () {
                    location.reload();
                },
                error: function () {
                    $(this).parent().append('<span class="badge badge-success"><fmt:message key="badge.paid"/></span>');
                }
            });

        });

        $('.button_change').click(function () {
            $(this).parent().find('button').show();
            $(this).hide();
            var tr_tariff_data= $(this).parent().parent().find('.tariff_data');
            tr_tariff_data.find('i').hide();
            tr_tariff_data.find('input').show();
        });
        $('.button_change_cancel').click(function () {
            $(this).parent().find('button').hide();
            $('.button_change').show();
            var tr_tariff_data= $(this).parent().parent().find('.tariff_data');
            tr_tariff_data.find('i').show();
            tr_tariff_data.find('input').hide();
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

    td img{
        width: 20px;
        height: 20px;
        opacity: 0.7;
    }
    td img:hover{
        width: 20px;
        height: 20px;
        opacity: 1;
        cursor: pointer;
    }
</style>

<head>
    <title>Admin</title>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/delivery"><fmt:message key="nav.home"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/delivery/admin"><fmt:message key="nav.profile"/></a>
            </li>
            <li class="nav-item">
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

<div class="container-fluid">
    <div class="row">

        <div class="jumbotron col-md-3" id="div_user_profile">

            <table class="table table-hover">
                <tbody>
                <tr class="table-light">
                    <th scope="row"><fmt:message key="input.login"/></th>
                    <td><c:out value="${sessionScope.user.getLogin()} "/></td>
                </tr>
                <tr class="table-light">
                    <th scope="row"><fmt:message key="input.email"/></th>
                    <td><c:out value="${sessionScope.user.getEmail()} "/></td>
                </tr>
                </tbody>
            </table>

        </div>

        <div class="jumbotron col-md-9">

            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="tariff.table.head.name"/></th>
                    <th scope="col"><fmt:message key="tariff.table.head.costPerKm"/></th>
                    <th scope="col"><fmt:message key="tariff.table.head.costPerKg"/></th>
                    <th scope="col"><fmt:message key="tariff.table.head.paceDayKm"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="tariff" items="${sessionScope.tariffList}">
                    <tr class="table-p">
                        <td><c:out value="${tariff.name} "/></td>
                        <td class="tariff_data"><i><c:out value="${tariff.costPerKm/100} "/></i><input class="costPerKm" type="number" step="0.01" min="0.01" value="${tariff.costPerKm/100}" style="display: none"></td>
                        <td class="tariff_data"><i><c:out value="${tariff.costPerKg/100} "/></i><input class="costPerKg" type="number" step="0.01" min="0.01" value="${tariff.costPerKg/100}" style="display: none"></td>
                        <td class="tariff_data"><i><c:out value="${tariff.paceDayKm} "/></i><input class="paceDayKm" type="number" step="1" min="1" value="${tariff.paceDayKm}" style="display: none"></td>
                        <td>
                            <button class="btn btn-sm btn-outline-primary button_change" >
                                <img title="change" src="${pageContext.request.contextPath}/resources/icons/change.png">
                            </button>
                            <button class="btn btn-sm btn-outline-success button_change_ok" value="${tariff.id}" style="display: none">
                                ok
                            </button>
                            <button class="btn btn-sm btn-outline-warning button_change_cancel" style="display: none">
                                cancel
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

  <%--          <button class="button btn btn-primary btn-sm"><fmt:message key="button.add.new"/></button>--%>

        </div>

    </div>
</div>
</body>
</html>
