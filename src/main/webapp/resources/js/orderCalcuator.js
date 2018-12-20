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