package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.AvailableOption;
import delivery.model.entity.Bill;
import delivery.model.entity.Order;
import delivery.model.entity.User;
import delivery.model.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class CreateOrderCommand implements Command {

    private CalculatorService calculatorService = new CalculatorServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private AvailableOptionService availableOptionService = new AvailableOptionServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String orderType = request.getParameter("orderType");
        String tariffId = request.getParameter("tariffId");
        String routeId = request.getParameter("routeId");
        String weightGr = request.getParameter("weight");

        Optional<AvailableOption> optionalAvailableOption = availableOptionService.getByRouteTariffId(Long.parseLong(routeId), Long.parseLong(tariffId));

        Order order = new Order();
        Bill bill = new Bill();
        order.setWeightGr(Integer.parseInt(weightGr));
        order.setType(Order.Type.valueOf(orderType));

        if(optionalAvailableOption.isPresent()){
            order.setAvailableOption(optionalAvailableOption.get());
            order.setArrivalDate(calculatorService.getDeliveryDate(optionalAvailableOption.get().getTariff(), optionalAvailableOption.get().getRoute()));

            bill.setTotal(calculatorService.getDeliveryPrice(optionalAvailableOption.get().getTariff(), optionalAvailableOption.get().getRoute(), Integer.parseInt(weightGr)));
        }

        bill.setOrder(order);
        order.setBill(bill);

        User user = (User)request.getSession().getAttribute("user");
        bill.setUser(user);
        order.setUser(user);

        orderService.create(order);

        response.setStatus(200);

        return null;
    }
}
