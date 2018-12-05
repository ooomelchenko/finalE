package delivery.controller.commands.actions;

import delivery.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getSession().getAttribute("userId") == null)
            return "guest:redirect";
        else if (request.getSession().getAttribute("userRole").equals("ADMIN"))
            return "admin:redirect";
        else if (request.getSession().getAttribute("userRole").equals("USER"))
            return "user:redirect";
        else
            return "guest:redirect";
    }
}
