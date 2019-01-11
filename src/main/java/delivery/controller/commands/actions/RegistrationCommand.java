package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.controller.exceptions.NotUniqUserException;
import delivery.model.entity.User;
import delivery.model.service.UserService;
import delivery.util.bundleManagers.ContentManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegistrationCommand implements Command {

    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String lang = (String) request.getSession().getAttribute("lang");

        String login = request.getParameter("login");

        if (login == null) {
            return "/WEB-INF/view/registration.jsp";
        }

        Map<String, String> fieldsMap = new ConcurrentHashMap<>();

        fieldsMap.put("login", login);
        fieldsMap.put("password", request.getParameter("password"));
        fieldsMap.put("firstname", request.getParameter("firstname"));
        fieldsMap.put("lastname", request.getParameter("lastname"));
        fieldsMap.put("email", request.getParameter("email"));

        Map<String, String> wrongFields = userService.validateFields(fieldsMap);

        User user = new User(fieldsMap.get("login"), fieldsMap.get("password"), fieldsMap.get("firstname"), fieldsMap.get("lastname"), fieldsMap.get("email"), User.Role.USER);

        if (wrongFields.isEmpty()) {
            try {
                userService.create(user);
                request.setAttribute("resultMessage", ContentManager.getProperty("registration.success", lang));
            } catch (NotUniqUserException e) {
                request.setAttribute("wrong_login", ContentManager.getProperty("registration.exception.notUniqUser", lang));
            } catch (RuntimeException re) {
                request.setAttribute("resultMessage", ContentManager.getProperty("exception.runtime", lang));
            }

            return "/WEB-INF/view/registration.jsp";

        } else {
            for (String field : wrongFields.keySet()) {

                request.setAttribute("wrong_" + field, ContentManager.getProperty("wrong." + field, lang));
            }
            request.setAttribute("userDTO", user);

            return "/WEB-INF/view/registration.jsp";
        }

    }
}