package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.User;
import delivery.model.service.UserService;
import delivery.model.service.UserServiceImpl;
import delivery.util.bundleManagers.ContentManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegistrationCommand implements Command {

    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");


        if (login == null) {
            return "/WEB-INF/view/registration.jsp";
        }

        Map<String, String> fieldsMap = new ConcurrentHashMap<>();

        fieldsMap.put("login", login);
        fieldsMap.put("password", password);
        fieldsMap.put("firstname", firstname);
        fieldsMap.put("lastname", lastname);
        fieldsMap.put("email", email);

        String lang = (String) request.getSession().getAttribute("lang");

        Map<String, String> wrongFields = userService.validateFields(fieldsMap);

        if (wrongFields.isEmpty()) {

            userService.create(login, password, firstname, lastname, email, User.Role.USER);
            request.setAttribute("resultMessage", ContentManager.getProperty("registration.success", lang));

            return "/WEB-INF/view/registration.jsp";
        } else {
            for (String field : wrongFields.keySet()) {

                request.setAttribute("wrong_"+field, ContentManager.getProperty("wrong."+field, lang));

            }
            request.setAttribute("userDTO", new User(login, password, firstname, lastname, email, User.Role.USER));

            return "/WEB-INF/view/registration.jsp";
        }

    }
}