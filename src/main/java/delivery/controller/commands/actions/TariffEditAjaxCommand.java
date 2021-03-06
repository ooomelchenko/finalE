package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.Tariff;
import delivery.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class TariffEditAjaxCommand implements Command {

    private TariffService tariffService;

    public TariffEditAjaxCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

            long tariffId = Long.parseLong((request.getParameter("tariffId")));
            long costPerKm = Long.parseLong(request.getParameter("costPerKm"));
            long costPerKg = Long.parseLong(request.getParameter("costPerKg"));
            int paceDayKm = Integer.parseInt(request.getParameter("paceDayKm"));

            Optional<Tariff> optionalTariff = tariffService.getTariff(tariffId);

            if(optionalTariff.isPresent()){

                Tariff tariff = optionalTariff.get();

                tariff.setCostPerKm(costPerKm);
                tariff.setCostPerKg(costPerKg);
                tariff.setPaceDayKm(paceDayKm);

                if(tariffService.update(tariff)) {
                    response.setStatus(200);
                }
                else response.setStatus(500);
            }
            else
                response.setStatus(501);

        return null;
    }
}

