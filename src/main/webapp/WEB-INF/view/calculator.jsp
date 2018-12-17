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
        $('#div_calc_res').hide();

        var orderType = $('#orderType');
        var routeId = $('#routeId');
        var tariffId = $('#tariffId');
        var inputWeight = $('#inputWeight');

        function isValidFieldset() {
            if(orderType.val()!=null && routeId.val()!=null && tariffId.val()!=null && inputWeight.val()>0)
                return true;
        }

        function getPrice_Date(){
            $.ajax({
                url: "${pageContext.request.contextPath}/delivery/calculate",
                method: "POST",
                data: {orderType: orderType.val(),
                    tariffId: tariffId.val(),
                    routeId: routeId.val(),
                    weight: inputWeight.val()*1000
                },
                dataType: "json",
                success: function (calculationJsonResults) {

                    $('#td_delivery_date').text(calculationJsonResults.arrivalDate);
                    $('#td_delivery_price').text(calculationJsonResults.deliveryPrice/100);

            },
            error: function () {
                $('#td_delivery_price').text("<fmt:message key="message.wrongaction"/>");
            }

        });
        }

        $('#button_send').click( function(){
            if(isValidFieldset()){
                $('#div_calc_res').show();
                $('#h_calc').hide();
                getPrice_Date();
            }
            else{
                alert('<fmt:message key="wrong.form.fieldset"/>');
            }

        });

    })
</script>

<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
      type="text/css">
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
    <title>Calculator</title>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/"><fmt:message key="nav.home"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01"
            aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/delivery/routes"><fmt:message key="nav.routes"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/delivery/tariffs"><fmt:message key="nav.tariffs"/></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/delivery/calculator"><fmt:message key="nav.calculator"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/delivery/about"><fmt:message key="nav.about"/></a>
            </li>
        </ul>
        <div>

            <c:if test="${sessionScope.user == null}">
                <a href="${pageContext.request.contextPath}/delivery/login">
                    <fmt:message key="input.login"/> <img
                        src="${pageContext.request.contextPath}/resources/icons/login.png"
                        style="width: 40px; height: 40px">
                </a>

            </c:if>
            <c:if test="${sessionScope.user != null}">
                <a href="${pageContext.request.contextPath}/delivery/logout">
                    <fmt:message key="logout.head"/> <img
                        src="${pageContext.request.contextPath}/resources/icons/logout.png"
                        style="width: 40px; height: 40px">
                </a>
            </c:if>

        </div>

        <div style="text-align: right" id="lang-div">
            <a href="?sessionLocale=en"><img
                    src="${pageContext.request.contextPath}/resources/icons/gb.png"><fmt:message key="lang.en"/></a>
            <br>
            <a href="?sessionLocale=ua"><img
                    src="${pageContext.request.contextPath}/resources/icons/ua.png"><fmt:message key="lang.ua"/></a>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">

        <div class="jumbotron col-3">
            <div id="div_calc_res">
                <h4>Результат розрахунку</h4>
                <table class="table table-hover">
                    <tbody>
                    <tr class="table-success">
                        <th scope="row">Дата доставки</th>
                        <td id="td_delivery_date"></td>
                    </tr>
                    <tr class="table-success">
                        <th scope="row">Стоимость доставки</th>
                        <td id="td_delivery_price"></td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <h4 id="h_calc"><fmt:message key="calculator.head"/></h4>

                <fieldset>
                    <div class="form-group">
                        <label for="orderType" class="col-sm-2 col-form-label"><fmt:message key="calculator.fieldset.orderType"/></label>
                        <select class="custom-select" id="orderType">
                            <c:forEach var="orderType" items="${sessionScope.orderTypes}">
                                <option value="${orderType.name()}">
                                        ${orderType.name()}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="routeId" class="col-sm-2 col-form-label"><fmt:message key="calculator.fieldset.route"/></label>
                        <select class="custom-select" id="routeId">
                            <c:forEach var="route" items="${sessionScope.routeList}">
                                <option value="${route.id}">
                                        ${route.routeStart}-${route.routeEnd}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="tariffId" class="col-sm-2 col-form-label"><fmt:message key="calculator.fieldset.tariff"/></label>
                        <select class="custom-select" id="tariffId">
                            <c:forEach var="tariff" items="${sessionScope.tariffList}">
                                <option value="${tariff.id}">
                                        ${tariff.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="inputWeight" class="col-sm-2 col-form-label"><fmt:message key="calculator.fieldset.weight"/></label>
                        <input class="form-control" id="inputWeight" type="number" step="0.1" min="0.1" max="10000" title="<fmt:message key="calculator.fieldset.weight"/>" placeholder="<fmt:message key="calculator.fieldset.weight.kg"/>">
                    </div>

                    <button id="button_send" type="button" class="btn btn-primary"><fmt:message key="button.calculate"/></button>
                </fieldset>


        </div>
        <div class="jumbotron col-5">
        </div>
        <div class="jumbotron col-4">
        </div>

    </div>
</div>

</body>

</html>