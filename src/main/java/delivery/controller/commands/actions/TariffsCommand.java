package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TariffsCommand implements Command {

    private TariffService tariffService;

    public TariffsCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("tariffList", tariffService.getAllTariffs());
        return "/WEB-INF/view/tariffsList.jsp";
    }
}