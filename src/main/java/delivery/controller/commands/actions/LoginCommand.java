package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.controller.commands.CommandEnum;
import delivery.util.bundleManagers.MessageManager;
import delivery.model.entity.User;
import delivery.model.service.UserService;
import delivery.model.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {

    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(request.getSession().getAttribute("user")!=null){
            return CommandEnum.EMPTY.getCurrentCommand().execute(request);
        }

        if( login == null || password == null){
            return "WEB-INF/view/login.jsp";
        }

        Optional<User> user = userService.login(login, password);
        if(user.isPresent()){
            request.getSession().setAttribute("user", user.get());
            return CommandEnum.EMPTY.getCurrentCommand().execute(request);
        }
        else {
            request.setAttribute("message", MessageManager.getProperty("login.wrongLoginData"));
            return "WEB-INF/view/login.jsp";
        }
    }
}