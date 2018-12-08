package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.util.bundleManagers.MessageManager;
import delivery.model.entity.User;
import delivery.model.service.UserService;
import delivery.model.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegistrationCommand implements Command {

    private UserService userService = new UserServiceImpl();
    private Map<String, String > fieldsMap = new ConcurrentHashMap<>();

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        if (login == null) {
            request.setAttribute("enumRoles", Arrays.stream(User.Role.values())
                    .filter(r -> !r.name().equals("GUEST"))
                    .toArray());
            return "WEB-INF/view/registration.jsp";
        }

        fieldsMap.put("login", login);
        fieldsMap.put("password", password);
        fieldsMap.put("firstname", firstname);
        fieldsMap.put("lastname", lastname);
        fieldsMap.put("email", email);

        Map<String, String> wrongFields = userService.validateFields(fieldsMap);
        System.out.println(wrongFields);
        if (wrongFields.isEmpty()) {
            userService.create(login, password, firstname, lastname, email, role);
            request.setAttribute("message", MessageManager.getProperty("registration.success"));
            return "login:redirect";
        } else {
            for (String field : wrongFields.keySet()) {
                request.setAttribute("wrong_" + field, MessageManager.getProperty("wrong." + field));
            }
            request.setAttribute("userDTO", new User(login, password, firstname, lastname, email, User.Role.valueOf(role)));
            request.setAttribute("enumRoles", Arrays.stream(User.Role.values())
                    .filter(r -> !r.name().equals("GUEST"))
                    .toArray());
            return "WEB-INF/view/registration.jsp";
        }

    }
}