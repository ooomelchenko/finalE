package delivery.controller.commands.actions;

import delivery.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class GuestCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
            return "WEB-INF/view/guest.jsp";
    }
}