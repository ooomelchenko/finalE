package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.Route;
import delivery.model.service.RouteService;
import delivery.util.bundleManagers.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Deprecated
public class RoutesCommand implements Command {

    @Autowired
    private RouteService routeService;

    /*private RouteService routeService = initRouteService();

    RouteService initRouteService() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        return (RouteService) context.getBean("routeServiceImpl");
    }*/

    public RoutesCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

       // ApplicationContext context = (ApplicationContext) request.getServletContext().getAttribute("springContext");
     //   routeService = (RouteService) context.getBean("routeServiceImpl");
        int portionSize = Integer.parseInt(ConfigurationManager.getProperty("page_portion"));

        List<Route> routeList = routeService.getAllRoutes()
                .stream()
                .sorted(Comparator.comparing(Route::getRouteStart))
                .collect(Collectors.toList());

        int countOfPortions =(int)Math.ceil((float)routeList.size() / portionSize);

        int portion = Integer.parseInt(Optional.ofNullable(request.getParameter("portion")).orElse("1"));

        int currentPortion = Math.min(portion, countOfPortions) ;

        int start = Math.max(0, (currentPortion-1)*portionSize);

        int end = Math.min(start+portionSize, routeList.size());

        request.setAttribute("routeList", routeList.subList(start,  end) );
        request.setAttribute("currentPortion", currentPortion);
        request.setAttribute("countOfPortions", countOfPortions);

        return "/WEB-INF/view/routesList.jsp";
    }
}