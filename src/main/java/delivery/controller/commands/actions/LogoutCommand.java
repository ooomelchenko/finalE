package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().removeAttribute("user");

        return "home:redirect";
    }
}
