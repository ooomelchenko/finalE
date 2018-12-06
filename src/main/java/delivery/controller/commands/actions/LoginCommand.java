package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.controller.commands.CommandEnum;
import delivery.controller.commands.MessageManager;
import delivery.model.entity.User;
import delivery.model.service.UserService;
import delivery.model.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {

    UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if( login == null || password == null){
            return "WEB-INF/view/login.jsp";
        }

        Optional<User> user = userService.login(login, password);
        if(user.isPresent()){
            request.getSession().setAttribute("userId", user.get().getId());
            request.getSession().setAttribute("userRole", user.get().getRole().name());
            return CommandEnum.EMPTY.getCurrentCommand().execute(request);
        }
        else {
            request.setAttribute("message", MessageManager.getProperty("login.wrongLoginData"));
            return "WEB-INF/view/login.jsp";
        }
    }
}