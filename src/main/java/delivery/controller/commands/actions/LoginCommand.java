package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.controller.commands.CommandEnum;
import delivery.model.entity.User;
import delivery.model.service.UserService;
import delivery.model.service.UserServiceImpl;
import delivery.util.bundleManagers.ContentManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {

    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response ) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(request.getSession().getAttribute("user")!=null){
            return CommandEnum.EMPTY.getCurrentCommand().execute(request, response);
        }

        if( login == null || password == null){
            return "/WEB-INF/view/login.jsp";
        }

        Optional<User> user = userService.login(login, password);
        if(user.isPresent()){
            request.getSession().setAttribute("user", user.get());
            return "home:redirect";
        }
        else {
            request.setAttribute("message", ContentManager.getProperty("login.wrongLoginData", (String) request.getSession().getAttribute("lang")));
            return "/WEB-INF/view/login.jsp";
        }
    }
}