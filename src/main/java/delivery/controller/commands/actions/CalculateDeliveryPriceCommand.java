package delivery.controller.commands.actions;

import com.google.gson.JsonObject;
import delivery.controller.commands.Command;
import delivery.model.entity.Order;
import delivery.model.entity.Route;
import delivery.model.entity.Tariff;
import delivery.model.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Optional;

public class CalculateDeliveryPriceCommand implements Command {

    private RouteService routeService = new RouteServiceImpl();
    private TariffService tariffService = new TariffServiceImpl();
    private CalculatorService calculatorService = new CalculatorServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String orderType = request.getParameter("orderType");
        String tariffId = request.getParameter("tariffId");
        String routeId = request.getParameter("routeId");
        String weightGr = request.getParameter("weight");

        if(!checkId(tariffId) || !checkId(routeId)
                || !checkOrderType(orderType) || !checkWeight(weightGr)){
            try {

                response.sendError(501, "check calculator fields");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Optional<Route> optionalRoute = routeService.getRoute(Long.parseLong(routeId));
        Optional<Tariff> optionalTariff = tariffService.getTariff(Long.parseLong(tariffId));
        Integer weight =  Integer.parseInt(weightGr);

        Tariff tariff = null;
        Route route = null;

        if(!optionalTariff.isPresent() || !optionalRoute.isPresent()){
            try {

                response.sendError(500, "route or tariff not founded");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            tariff = optionalTariff.get();
            route = optionalRoute.get();
        }

        Long price = calculatorService.getDeliveryPrice(tariff, route, weight);
        LocalDate deliveryDate = calculatorService.getDeliveryDate(tariff, route);

        response.setContentType("application/json");

        JsonObject calculationJsonResults = new JsonObject();

        calculationJsonResults.addProperty("arrivalDate", String.valueOf(deliveryDate));
        calculationJsonResults.addProperty("deliveryPrice", price);

        try (PrintWriter writer = response.getWriter()) {

            writer.print(calculationJsonResults);

        } catch (Exception e) {

            response.setStatus(200);
        }
        return null;
    }

    private boolean checkId(String id) {
        try {
            return Long.parseLong(id) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checkOrderType(String orderType) {
        try {
            Order.Type.valueOf(orderType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean checkWeight(String weight) {
        try {
            return Integer.parseInt(weight) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
