package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.Order;
import delivery.model.service.RouteService;
import delivery.model.service.RouteServiceImpl;
import delivery.model.service.TariffService;
import delivery.model.service.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderCreatorCommand implements Command {

    private RouteService routeService = new RouteServiceImpl();
    private TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("orderTypes", Order.Type.values());
        request.setAttribute("routeList", routeService.getAllRoutes());
        request.setAttribute("tariffList", tariffService.getAllTariffs());
        return "/WEB-INF/view/user/orderCreator.jsp";
        
    }
}
