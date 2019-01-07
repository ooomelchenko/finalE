package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.Route;
import delivery.model.service.RouteService;
import delivery.model.service.RouteServiceImpl;
import delivery.model.service.TariffService;
import delivery.model.service.TariffServiceImpl;
import delivery.util.bundleManagers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RoutesManagerCommand implements Command {

    private RouteService routeService = new RouteServiceImpl();
    private TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int portionSize = Integer.parseInt(ConfigurationManager.getProperty("page_portion"));

        List<Route> routeList = routeService.getAllRoutes();

        int countOfPortions = (routeList.size() % portionSize > 0) ? routeList.size() / portionSize + 1 : routeList.size() / portionSize ;

        int portion =1;

        try {
            portion = Math.min(Integer.parseInt(request.getParameter("portion")),countOfPortions) ;
        }
        catch (NumberFormatException ignored){
        }

        int start = (portion-1)*portionSize;

        int end = Math.min(start+portionSize, routeList.size());

        request.setAttribute("routeList", routeList.subList(start,  end) );
        request.setAttribute("currentPortion", portion);
        request.setAttribute("countOfPortions", countOfPortions);

        request.setAttribute("tariffList", tariffService.getAllTariffs());

        return "/WEB-INF/view/admin/routesManager.jsp";
    }
}