package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.User;
import delivery.model.service.OrderService;
import delivery.model.service.OrderServiceImpl;
import delivery.model.service.UserService;
import delivery.model.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class UserCommand implements Command {

    private UserService userService = new UserServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute("user");

        Optional<User> optionalUser = userService.getById(user.getId());

        if(optionalUser.isPresent()){
            user = optionalUser.get();
            user.setOrders(orderService.findByUserId(optionalUser.get().getId()));
        }

        request.setAttribute("userFull", user);

        return "/WEB-INF/view/user/userMenu.jsp";
    }
}