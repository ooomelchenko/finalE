package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if( login == null || login.equals("") || password == null || password.equals("")  ){
            return "WEB-INF/view/login.jsp";
        }

        return "WEB-INF/view/login.jsp";
    }
}