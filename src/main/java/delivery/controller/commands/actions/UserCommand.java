package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.User;
import delivery.model.service.OrderService;
import delivery.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class UserCommand implements Command {

    private UserService userService;
    private OrderService orderService;

    public UserCommand(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

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