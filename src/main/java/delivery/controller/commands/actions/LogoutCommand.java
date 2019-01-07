package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Map<String, HttpSession> loggedUsers = (ConcurrentHashMap<String, HttpSession>) request.getServletContext().getAttribute("loggedUsers");

        User user = (User) request.getSession().getAttribute("user");

        loggedUsers.remove(user.getLogin());

        request.getServletContext().setAttribute("loggedUsers", loggedUsers);

        request.getSession().removeAttribute("user");

        return ":redirect";
    }
}