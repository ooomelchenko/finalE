package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.User;
import delivery.model.service.UserService;
import delivery.model.service.UserServiceImpl;
import delivery.util.bundleManagers.ContentManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AccountRefillCommand implements Command {

    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = (User)request.getSession().getAttribute("user");
        System.out.println(request.getParameter("payment"));
        long payment = Long.parseLong(request.getParameter("payment"));
        String lang = (String) request.getSession().getAttribute("lang");

        if (userService.refill(user, payment)){

            try(PrintWriter writer = response.getWriter()){
                writer.print(ContentManager.getProperty("payment.success", lang));
            } catch (IOException e) {
                e.printStackTrace();
            }
            response.setStatus(200);
        }
        else response.setStatus(500);

        return null;
    }
}