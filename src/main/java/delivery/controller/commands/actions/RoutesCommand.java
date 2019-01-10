package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.Route;
import delivery.model.service.RouteService;
import delivery.util.bundleManagers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class RoutesCommand implements Command {

    private RouteService routeService ;

    public RoutesCommand(RouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int portionSize = Integer.parseInt(ConfigurationManager.getProperty("page_portion"));

        List<Route> routeList = routeService.getAllRoutes();

        int countOfPortions =(int)Math.ceil((float)routeList.size() / portionSize);

        int portions = Integer.parseInt(Optional.ofNullable(request.getParameter("portion")).orElse("1"));

        int currentPortion = Math.min(portions, countOfPortions) ;

        int start = (currentPortion-1)*portionSize;

        int end = Math.min(start+portionSize, routeList.size());

        request.setAttribute("routeList", routeList.subList(start,  end) );
        request.setAttribute("currentPortion", currentPortion);
        request.setAttribute("countOfPortions", countOfPortions);

        return "/WEB-INF/view/routesList.jsp";
    }
}