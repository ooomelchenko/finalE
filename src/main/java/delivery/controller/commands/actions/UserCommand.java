package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.Order;
import delivery.model.entity.User;
import delivery.model.service.OrderService;
import delivery.model.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserCommand implements Command {


    private OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute("user");

        List<Order> orderList = orderService.findByUserId(user.getId());

        request.setAttribute("orderList", orderList);

        return "/WEB-INF/view/user/userMenu.jsp";
    }
}