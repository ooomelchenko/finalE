package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.service.TariffService;
import delivery.model.service.TariffServiceImpl;
import javax.servlet.http.HttpServletRequest;

public class TariffsCommand implements Command {

    private TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("tariffList", tariffService.getAllTariffs());
        return "/WEB-INF/view/tariffsList.jsp";
    }
}