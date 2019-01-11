package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.Route;
import delivery.model.service.RouteService;
import delivery.model.service.TariffService;
import delivery.util.bundleManagers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoutesManagerCommand implements Command {

    private RouteService routeService;
    private TariffService tariffService;

    public RoutesManagerCommand(RouteService routeService, TariffService tariffService) {
        this.routeService = routeService;
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int portionSize = Integer.parseInt(ConfigurationManager.getProperty("page_portion"));

        List<Route> routeList = routeService.getAllRoutes().stream()
                .sorted(Comparator.comparing(Route::getRouteStart))
                .collect(Collectors.toList());

        int countOfPortions =(int)Math.ceil((float)routeList.size() / portionSize);

        int portions = Integer.parseInt(Optional.ofNullable(request.getParameter("portion")).orElse("1"));

        int currentPortion = Math.min(portions, countOfPortions) ;

        int start = (currentPortion-1)*portionSize;

        int end = Math.min(start+portionSize, routeList.size());

        request.setAttribute("routeList", routeList.subList(start,  end) );
        request.setAttribute("currentPortion", currentPortion);
        request.setAttribute("countOfPortions", countOfPortions);

        request.setAttribute("tariffList", tariffService.getAllTariffs());

        return "/WEB-INF/view/admin/routesManager.jsp";
    }
}