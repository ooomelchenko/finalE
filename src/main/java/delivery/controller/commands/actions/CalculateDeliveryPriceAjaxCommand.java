package delivery.controller.commands.actions;

import com.google.gson.JsonObject;
import delivery.controller.commands.Command;
import delivery.model.entity.Route;
import delivery.model.entity.Tariff;
import delivery.model.service.CalculatorService;
import delivery.model.service.RouteService;
import delivery.model.service.TariffService;
import delivery.util.bundleManagers.ContentManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Optional;

public class CalculateDeliveryPriceAjaxCommand implements Command {

    private RouteService routeService;
    private TariffService tariffService;
    private CalculatorService calculatorService;

    public CalculateDeliveryPriceAjaxCommand(RouteService routeService, TariffService tariffService, CalculatorService calculatorService) {
        this.routeService = routeService;
        this.tariffService = tariffService;
        this.calculatorService = calculatorService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String lang = (String) request.getSession().getAttribute("lang");

        String tariffId = request.getParameter("tariffId");
        String routeId = request.getParameter("routeId");
        String weightGr = request.getParameter("weight");

        Optional<Route> optionalRoute = routeService.getRoute(Long.parseLong(routeId));

        Optional<Tariff> optionalTariff = tariffService.getTariff(Long.parseLong(tariffId));

        Integer weight = Integer.parseInt(weightGr);

        try (PrintWriter writer = response.getWriter()) {

            if (optionalTariff.isPresent() && optionalRoute.isPresent()) {
                Long price = calculatorService.getDeliveryPrice(optionalTariff.get(), optionalRoute.get(), weight);
                LocalDate deliveryDate = calculatorService.getDeliveryDate(optionalTariff.get(), optionalRoute.get());

                response.setContentType("application/json");
                JsonObject calculationJsonResults = new JsonObject();

                calculationJsonResults.addProperty("arrivalDate", String.valueOf(deliveryDate));
                calculationJsonResults.addProperty("deliveryPrice", price);

                writer.print(calculationJsonResults);
                response.setStatus(200);
            } else {
                writer.print(ContentManager.getProperty("option.tariff.available.not", lang));
                response.setStatus(500);
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(502);
        }
        return null;
    }

}
