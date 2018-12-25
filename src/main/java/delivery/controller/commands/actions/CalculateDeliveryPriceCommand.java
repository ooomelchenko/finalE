package delivery.controller.commands.actions;

import com.google.gson.JsonObject;
import delivery.controller.commands.Command;
import delivery.controller.exceptions.WrongCommandException;
import delivery.model.entity.Route;
import delivery.model.entity.Tariff;
import delivery.model.service.*;
import delivery.util.ValidatorParams;

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
    private ValidatorParams validator = new ValidatorParams();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws WrongCommandException {
        String orderType = request.getParameter("orderType");
        String tariffId = request.getParameter("tariffId");
        String routeId = request.getParameter("routeId");
        String weightGr = request.getParameter("weight");

        if(!validator.checkId(tariffId) || !validator.checkId(routeId)
                || !validator.checkOrderType(orderType) || !validator.checkWeight(weightGr)){
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

        calculationJsonResults.addProperty("arrivalDate", deliveryDate.toString());
        calculationJsonResults.addProperty("deliveryPrice", price);

        try (PrintWriter writer = response.getWriter()) {

            writer.print(calculationJsonResults);

        } catch (Exception e) {
            throw new WrongCommandException("CalculateDeliveryPriceCommand");
        }
        return null;
    }
}
