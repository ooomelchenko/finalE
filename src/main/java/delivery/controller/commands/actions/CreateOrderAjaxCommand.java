package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.AvailableOption;
import delivery.model.entity.Bill;
import delivery.model.entity.Order;
import delivery.model.entity.User;
import delivery.model.service.*;
import delivery.util.bundleManagers.ContentManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class CreateOrderAjaxCommand implements Command {

    private CalculatorService calculatorService ;
    private OrderService orderService ;
    private AvailableOptionService availableOptionService ;

    public CreateOrderAjaxCommand(CalculatorService calculatorService, OrderService orderService, AvailableOptionService availableOptionService) {
        this.calculatorService = calculatorService;
        this.orderService = orderService;
        this.availableOptionService = availableOptionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String lang = (String) request.getSession().getAttribute("lang");

        String orderType = request.getParameter("orderType");
        String tariffId = request.getParameter("tariffId");
        String routeId = request.getParameter("routeId");
        String weightGr = request.getParameter("weight");

        Optional<AvailableOption> optionalAvailableOption = availableOptionService.getByRouteTariffId(Long.parseLong(routeId), Long.parseLong(tariffId));

        Order order = new Order();
        Bill bill = new Bill();
        order.setWeightGr(Integer.parseInt(weightGr));
        order.setType(Order.Type.valueOf(orderType));

        try (PrintWriter writer = response.getWriter()) {

            if(optionalAvailableOption.isPresent()){
                order.setAvailableOption(optionalAvailableOption.get());
                order.setArrivalDate(calculatorService.getDeliveryDate(optionalAvailableOption.get().getTariff(), optionalAvailableOption.get().getRoute()));

                bill.setTotal(calculatorService.getDeliveryPrice(optionalAvailableOption.get().getTariff(), optionalAvailableOption.get().getRoute(), Integer.parseInt(weightGr)));
            }
            else{
                writer.print(ContentManager.getProperty("option.tariff.available.not", lang));
                response.setStatus(500);
            }

            bill.setOrder(order);
            order.setBill(bill);

            User user = (User)request.getSession().getAttribute("user");
            bill.setUser(user);
            order.setUser(user);
            try{
                orderService.create(order);
                response.setStatus(200);
            }
            catch (RuntimeException e){
                writer.print(ContentManager.getProperty("exception.runtime", lang));
                response.setStatus(500);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
