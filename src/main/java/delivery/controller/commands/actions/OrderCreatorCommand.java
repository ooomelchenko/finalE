package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.Order;
import delivery.model.service.RouteService;
import delivery.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderCreatorCommand implements Command {

    private RouteService routeService ;
    private TariffService tariffService ;

    public OrderCreatorCommand(RouteService routeService, TariffService tariffService) {
        this.routeService = routeService;
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("orderTypes", Order.Type.values());
        request.setAttribute("routeList", routeService.getAllRoutes());
        request.setAttribute("tariffList", tariffService.getAllTariffs());

        return "/WEB-INF/view/user/orderCreator.jsp";
        
    }
}
