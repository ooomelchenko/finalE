package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.Order;
import delivery.model.entity.Route;
import delivery.model.service.RouteService;
import delivery.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CalculatorCommand implements Command {

    private RouteService routeService;
    private TariffService tariffService;

    public CalculatorCommand(RouteService routeService, TariffService tariffService) {
        this.routeService = routeService;
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("orderTypes", Order.Type.values());

        request.setAttribute("routeList", routeService.getAllRoutes().stream()
                .sorted(Comparator.comparing(Route::getRouteStart))
                .collect(Collectors.toList()) );

        request.setAttribute("tariffList", tariffService.getAllTariffs());

        return "/WEB-INF/view/calculator.jsp";
    }
}