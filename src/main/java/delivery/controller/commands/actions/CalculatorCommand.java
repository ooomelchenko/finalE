package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.Order;
import delivery.model.service.RouteService;
import delivery.model.service.RouteServiceImpl;
import delivery.model.service.TariffService;
import delivery.model.service.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorCommand implements Command {

    private RouteService routeService = new RouteServiceImpl();
    private TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute("tariffList", tariffService.getAllTariffs());
        request.getSession().setAttribute("routeList", routeService.getAllRoutes());
        request.getSession().setAttribute("orderTypes", Order.Type.values());

        return "/WEB-INF/view/calculator.jsp";
    }
}
