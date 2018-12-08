package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EmptyCommand implements Command {

    private Map<String, String> pathMap = new ConcurrentHashMap<>();
    {
        pathMap.put(User.Role.USER.name(), "user:redirect");
        pathMap.put(User.Role.ADMIN.name(), "admin:redirect");
    }

    @Override
    public String execute(HttpServletRequest request) {

        try {
            return pathMap.getOrDefault(((User) request.getSession().getAttribute("user")).getRole().name(), "WEB-INF/view/index.jsp");
        }
        catch (NullPointerException e){
            return "WEB-INF/view/index.jsp";
        }
    }

}