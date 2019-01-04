package delivery.controller.commands.actions;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import delivery.controller.commands.Command;
import delivery.model.entity.AvailableOption;
import delivery.model.entity.Route;
import delivery.model.entity.Tariff;
import delivery.model.service.AvailableOptionService;
import delivery.model.service.AvailableOptionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class OptionEditCommand implements Command {

    private AvailableOptionService availableOptionService = new AvailableOptionServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<AvailableOption> optionList = new ArrayList<>();

        long routeId = Long.parseLong((request.getParameter("routeId")));
        Route route = new Route();
        route.setId(routeId);

        JsonElement jsonElement = new JsonParser().parse(request.getParameter("tariffArray"));

        for(JsonElement json: jsonElement.getAsJsonArray()){
            long tariffId = json.getAsJsonObject().get("id").getAsLong();
            boolean isAvailable = json.getAsJsonObject().get("isAvailable").getAsBoolean();
            Tariff tariff = new Tariff();
            tariff.setId(tariffId);
            optionList.add(new AvailableOption(route, tariff, isAvailable));
        }

        availableOptionService.updateOrInsert(optionList);
        return null;
    }
}
