package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.service.RouteService;
import delivery.model.service.RouteServiceImpl;
import javax.servlet.http.HttpServletRequest;

public class RoutesCommand implements Command {

    private RouteService routeService = new RouteServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("routeList", routeService.getAllRoutes());
        return "/WEB-INF/view/routesList.jsp";
    }
}