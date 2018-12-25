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
        $('.alert').hide();
        $('#div_calc_res').hide();

        var orderType = $('#orderType');
        var inputWeight = $('#inputWeight');
        var route_select = $('#routeId');
        var tariff_select = $('#tariffId');

        var button_create_order = $('#button_create_order');

        function isValidFieldset() {
            if(orderType.val()!=null && route_select.val()!=null && tariff_select.val()!=null && inputWeight.val()>0)
                return true;
        }

        function getPrice_Date(){
            $.ajax({
                url: "${pageContext.request.contextPath}/delivery/calculate",
                method: "POST",
                data: {orderType: orderType.val(),
                    tariffId: tariff_select.val(),
                    routeId: route_select.val(),
                    weight: inputWeight.val()*1000
                },
                dataType: "json",
                success: function (calculationJsonResults) {

                    $('#td_delivery_date').text(calculationJsonResults.arrivalDate);
                    $('#td_delivery_price').text(calculationJsonResults.deliveryPrice/100);

                },
                error: function () {
                    alert('error');
                }

            });
        }

        $('#button_send').click( function(){
            if(isValidFieldset()){
                $('#div_calc_res').show();
                button_create_order.show();
                $('#h_calc').hide();
                getPrice_Date();
            }
            else{

            }

        });

        getTariffList();

        route_select.change(function () {
            tariff_select.empty();
            getTariffList();
        });
        function getTariffList(){
            $.ajax({
                url: "${pageContext.request.contextPath}/delivery/getTariffListByRoute",
                method: "POST",
                data: {routeId: route_select.val()},
                dataType: "json",
                success: function (tariffs) {

                    $.each(tariffs, function(){
                        tariff_select.append("<option value=\""+this.id+"\">" +
                            this.name +
                            "</option>");
                    });

                },
                error: function () {
                    tariff_select.append("<option value=null><fmt:message key="option.tariff.available.not"/></option>");
                }

            });
        }

        button_create_order.click(function () {
            $(this).hide();
                $.ajax({
                    url: "${pageContext.request.contextPath}/delivery/user/createOrder",
                    method: "POST",
                    data: {orderType: orderType.val(),
                        tariffId: tariff_select.val(),
                        routeId: route_select.val(),
                        weight: inputWeight.val()*1000
                    },

                    success: function () {
                        $("#div_success_orderCreate").show();
                    },
                    error: function () {
                        $("#div_error_orderCreate").show();
                    }
                });

        })
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
    <title>Order</title>
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
                <a class="nav-link" href="${pageContext.request.contextPath}/delivery/user"><fmt:message key="nav.profile"/></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/delivery/user/ordercreator"><fmt:message key="nav.order.creator"/></a>
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

        <div class="jumbotron col-3">

            <h4 id="h_calc"><fmt:message key="calculator.head"/></h4>

            <fieldset>
                <div class="form-group">
                    <label for="orderType" class="col-sm-2 col-form-label"><fmt:message key="calculator.fieldset.orderType"/></label>
                    <select class="custom-select" id="orderType">
                        <c:forEach var="orderType" items="${requestScope.orderTypes}">
                            <option value="${orderType.name()}">
                                    ${orderType.name()}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="routeId" class="col-sm-2 col-form-label"><fmt:message key="calculator.fieldset.route"/></label>
                    <select class="custom-select" id="routeId">
                        <c:forEach var="route" items="${requestScope.routeList}">
                            <option value="${route.id}">
                                    ${route.routeStart}-${route.routeEnd}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="tariffId" class="col-sm-2 col-form-label"><fmt:message key="calculator.fieldset.tariff"/></label>
                    <select class="custom-select" id="tariffId">

                    </select>
                </div>
                <div class="form-group">
                    <label for="inputWeight" class="col-sm-2 col-form-label"><fmt:message key="calculator.fieldset.weight"/></label>
                    <input class="form-control" id="inputWeight" type="number" step="0.1" min="0.1" max="10000" title="<fmt:message key="calculator.fieldset.weight"/>" placeholder="<fmt:message key="calculator.fieldset.weight.kg"/>">
                </div>

                <button id="button_send" type="button" class="btn btn-primary"><fmt:message key="button.calculate"/></button>
            </fieldset>


        </div>

        <div class="jumbotron col-5" id="div_results">

            <div id="div_success_orderCreate" class="alert alert-dismissible alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong title="go to orders"><a href="${pageContext.request.contextPath}/delivery/user" class="alert-link"><fmt:message key="order.message.create.success"/></a> </strong>
                <br/>
            </div>
            <div id="div_error_orderCreate" class="alert alert-dismissible alert-danger">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong><fmt:message key="order.message.create.error"/> </strong>
                <br/>
            </div>

            <div id="div_calc_res">
                <h3><fmt:message key="calculator.head.result"/></h3>
                <table class="table table-hover">
                    <tbody>
                    <tr class="table-success">
                        <th scope="row"><fmt:message key="calculator.result.date"/></th>
                        <td id="td_delivery_date"></td>
                    </tr>
                    <tr class="table-success">
                        <th scope="row"><fmt:message key="calculator.result.price"/></th>
                        <td id="td_delivery_price"></td>
                    </tr>
                    </tbody>
                </table>
                <button id="button_create_order" type="button" class="btn btn-success"><fmt:message key="button.order"/></button>
            </div>
        </div>

        <div class="jumbotron col-4">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="tariff.table.head.name"/></th>
                    <th scope="col"><fmt:message key="tariff.table.head.costPerKm"/></th>
                    <th scope="col"><fmt:message key="tariff.table.head.costPerKg"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="tariff" items="${requestScope.tariffList}">
                    <tr class="table-p">
                        <td><c:out value="${tariff.name} "/></td>
                        <td><c:out value="${tariff.costPerKm/100} "/></td>
                        <td><c:out value="${tariff.costPerKg/100} "/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>
</div>

</body>

</html>