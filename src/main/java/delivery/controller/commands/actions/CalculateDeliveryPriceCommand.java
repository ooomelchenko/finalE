package delivery.controller.commands.actions;

import com.google.gson.JsonObject;
import delivery.controller.commands.Command;
import delivery.exceptions.WrongCommandException;
import delivery.model.entity.Route;
import delivery.model.entity.Tariff;
import delivery.model.service.RouteService;
import delivery.model.service.RouteServiceImpl;
import delivery.model.service.TariffService;
import delivery.model.service.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

public class CalculateDeliveryPriceCommand implements Command {

    private RouteService routeService = new RouteServiceImpl();
    private TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws WrongCommandException {
        String orderType = request.getParameter("orderType");
        String tariffId = request.getParameter("tariffId");
        String routeId = request.getParameter("routeId");
        String weightGr = request.getParameter("weight");

        Optional<Route> optionalRoute = routeService.getRoute(Long.parseLong(routeId));
        Optional<Tariff> optionalTariff = tariffService.getTariff(Long.parseLong(tariffId));
        Long weight =  Long.valueOf(weightGr);

        Tariff tariff = optionalTariff.get();
        Route route = optionalRoute.get();

        Long price = (tariff.getCostPerKg()*weight)/1000 + route.getDistanceKm()*tariff.getCostPerKm();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        response.setContentType("application/json");

        JsonObject calculationJsonResults = new JsonObject();

        calculationJsonResults.addProperty("arrivalDate", sdf.format(new Date()));
        calculationJsonResults.addProperty("deliveryPrice", price);

        try (PrintWriter writer = response.getWriter()) {

            writer.print(calculationJsonResults);

        } catch (Exception e) {
            throw new WrongCommandException("CalculateDeliveryPriceCommand");
        }
        return null;
    }
}
