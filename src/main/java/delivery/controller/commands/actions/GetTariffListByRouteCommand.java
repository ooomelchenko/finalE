package delivery.controller.commands.actions;

import com.google.gson.Gson;
import delivery.controller.commands.Command;
import delivery.controller.exceptions.WrongCommandException;
import delivery.model.entity.Route;
import delivery.model.entity.Tariff;
import delivery.model.service.RouteService;
import delivery.model.service.RouteServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetTariffListByRouteCommand implements Command {

    private RouteService routeService = new RouteServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String routeId = request.getParameter("routeId");

        Optional<Route> route = routeService.getRoute(Long.parseLong(routeId));

        List<Tariff> tariffList = new ArrayList<>();
        Tariff tariff1 = new Tariff();
        Tariff tariff2 = new Tariff();
        Tariff tariff3 = new Tariff();
        tariff1.setName("Standart");
        tariff1.setId(1);
        tariff2.setName("Fast");
        tariff2.setId(2);
        tariff3.setName("Express");
        tariff3.setId(3);

        tariffList.add(tariff1);
        tariffList.add(tariff2);
        tariffList.add(tariff3);

        response.setContentType("application/json");

        Gson gson = new Gson();

        String stringJson = gson.toJson(tariffList);

        if (route.isPresent()) {

            try (PrintWriter writer = response.getWriter()) {

                writer.print(stringJson);

            } catch (Exception e) {
                throw new WrongCommandException("responce writer trouble");
            }
        }

        return null;
    }
}
