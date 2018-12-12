package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        request.getSession().removeAttribute("user");

        return "home:redirect";
    }
}
