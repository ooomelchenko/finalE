package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.Order;
import delivery.model.service.RouteService;
import delivery.model.service.RouteServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCommand implements Command {

    private RouteService routeService = new RouteServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("orderTypes", Order.Type.values());
        request.setAttribute("routeList", routeService.getAllRoutes());

        return "/WEB-INF/view/user/userMenu.jsp";
    }
}