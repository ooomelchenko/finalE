package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.controller.exceptions.RoleAccessDeniedCommandException;
import delivery.controller.exceptions.WrongCommandException;
import delivery.model.entity.User;
import delivery.model.service.UserService;
import delivery.util.bundleManagers.ContentManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LoginCommand implements Command {

    private UserService userService ;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws RoleAccessDeniedCommandException, WrongCommandException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (request.getSession().getAttribute("user") != null) {
            throw new WrongCommandException("User already logged");
        }

        if (login == null || password == null) {
            return "/WEB-INF/view/login.jsp";
        }

        Optional<User> user = userService.login(login, password);

        if (user.isPresent()) {

            Map<String, HttpSession> loggedUsers = (ConcurrentHashMap<String, HttpSession>) request.getServletContext().getAttribute("loggedUsers");
            if (loggedUsers.containsKey(user.get().getLogin())) {
                throw new RoleAccessDeniedCommandException("User already logged !");
            }

            loggedUsers.put(user.get().getLogin(), request.getSession());
            request.getServletContext().setAttribute("loggedUsers", loggedUsers);

            user.get().setPassword(null);
            request.getSession().setAttribute("user", user.get());
            return ":redirect";
        } else {
            request.setAttribute("message", ContentManager.getProperty("login.wrongLoginData", (String) request.getSession().getAttribute("lang")));
            return "/WEB-INF/view/login.jsp";
        }
    }
}