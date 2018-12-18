package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute("loggedUsers");

        User user = (User) request.getSession().getAttribute("user");

        loggedUsers.remove(user.getLogin());

        request.getServletContext().setAttribute("loggedUsers", loggedUsers);

        request.getSession().removeAttribute("user");

        return ":redirect";
    }
}