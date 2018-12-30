package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.service.TariffService;
import delivery.model.service.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminCommand implements Command {

    private TariffService tariffService = new TariffServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute("tariffList", tariffService.getAllTariffs());

        return "/WEB-INF/view/admin/adminMenu.jsp";
    }
}