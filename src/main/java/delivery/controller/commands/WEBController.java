package delivery.controller.commands;

import delivery.model.entity.Route;
import delivery.model.service.RouteService;
import delivery.model.service.UserService;
import delivery.util.bundleManagers.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/delivery")
public class WEBController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
    private String main() {
        Locale.setDefault(Locale.ENGLISH);
        return "home";
    }

    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    private String login(HttpServletRequest request, HttpServletResponse response){
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (request.getSession().getAttribute("user") != null) {
            throw new WrongCommandException("User already logged");
        }

        if (login == null || password == null) {
            return "login";
        }

        Optional<User> user = userService.login(login, password);

        if (user.isPresent()) {

            Map<String, HttpSession> loggedUsers = (ConcurrentHashMap<String, HttpSession>) request.getServletContext().getAttribute("loggedUsers");
            if (loggedUsers.containsKey(user.get().getLogin())) {
                throw new RoleAccessDeniedCommandException("User already logged !");
            }

            loggedUsers.put(user.get().getLogin(), request.getSession());
            request.getServletContext().setAttribute("loggedUsers", loggedUsers);

            user.get().setPassword(null);
            request.getSession().setAttribute("user", user.get());
            return ":redirect";
        } else {
            request.setAttribute("message", ContentManager.getProperty("login.wrongLoginData", (String) request.getSession().getAttribute("lang")));
            return "login";
        }
    }*/

    @RequestMapping(value = "/routes", method = RequestMethod.GET)
    private String getRoutes(HttpServletRequest request){
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

        return "routesList";

    }


}
