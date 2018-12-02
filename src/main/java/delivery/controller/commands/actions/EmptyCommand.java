package delivery.controller.commands.actions;

import delivery.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null)
            return "guest:redirect";
        else if (request.getSession().getAttribute("userRole").equals("admin"))
            return "admin:redirect";
        else return "user:redirect";
    }
}
