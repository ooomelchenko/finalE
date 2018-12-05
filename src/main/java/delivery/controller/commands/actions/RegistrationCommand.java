package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.controller.commands.MessageManager;
import delivery.model.entity.User;
import delivery.model.service.UserService;
import delivery.model.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class RegistrationCommand implements Command {

    UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        if (login==null) {
            request.setAttribute("enumRoles", Arrays.stream(User.Role.values())
                    .filter(r->!r.name().equals("GUEST"))
                    .toArray());
            return "WEB-INF/view/registration.jsp";
        }
        else {
            List<String> wrongFields = userService.validateFields(login, password, firstname, lastname, email, role);
            if (wrongFields!=null){
                userService.create(login, password, firstname, lastname, email, role);
                request.setAttribute("message", MessageManager.getProperty("registration.success"));
                return "login:redirect";
            }
            else{
                /*for(String field: wrongFields){
                    request.setAttribute("wrong."+field, MessageManager.getProperty("wrong."+field));
                }*/
                request.setAttribute("userDTO", new User(login, password, firstname, lastname, email, User.Role.valueOf("USER")));
                request.setAttribute("enumRoles", Arrays.stream(User.Role.values())
                        .filter(r->!r.name().equals("GUEST"))
                        .toArray());
                return "WEB-INF/view/registration.jsp";
            }
        }
    }
}