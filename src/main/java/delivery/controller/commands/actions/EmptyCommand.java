package delivery.controller.commands.actions;

import delivery.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand implements Command {

/*    private Map<String, String> pathMap = new ConcurrentHashMap<>();
    {
        pathMap.put(User.Role.USER.name(), "delivery/user:redirect");
        pathMap.put(User.Role.ADMIN.name(), "delivery/admin:redirect");
    }*/

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

    /*    try {
            return pathMap.getOrDefault(((User) request.getSession().getAttribute("user")).getRole().name(), "/WEB-INF/view/home.jsp");
        }
        catch (NullPointerException e){
            return "/WEB-INF/view/home.jsp";
        }*/
        return "/WEB-INF/view/home.jsp";
    }

}