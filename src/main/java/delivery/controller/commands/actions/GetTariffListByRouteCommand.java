package delivery.controller.commands.actions;

import com.google.gson.Gson;
import delivery.controller.commands.Command;
import delivery.model.entity.Tariff;
import delivery.model.service.TariffService;
import delivery.model.service.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

public class GetTariffListByRouteCommand implements Command {

    private TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String routeId = request.getParameter("routeId");

        List<Tariff> tariffList = tariffService.findAvailableByRouteId(Long.parseLong(routeId));

        response.setContentType("application/json");

        Gson gson = new Gson();

        String stringJson = gson.toJson(tariffList);

            try (PrintWriter writer = response.getWriter()) {

                writer.print(stringJson);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        return null;
    }
}
