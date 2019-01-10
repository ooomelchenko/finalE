package delivery.controller.commands.actions;

import com.google.gson.Gson;
import delivery.controller.commands.Command;
import delivery.model.entity.Tariff;
import delivery.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetTariffListByRouteAjaxCommand implements Command {

    private TariffService tariffService;

    public GetTariffListByRouteAjaxCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String routeId = request.getParameter("routeId");

        List<Tariff> tariffList = tariffService.findAvailableByRouteId(Long.parseLong(routeId));

        response.setContentType("application/json");

        Gson gson = new Gson();

        String stringJson = gson.toJson(tariffList);

            try (PrintWriter writer = response.getWriter()) {

                writer.print(stringJson);

            } catch (IOException e) {

                response.setStatus(500);

            }

        return null;
    }
}
