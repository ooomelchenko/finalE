package delivery.model.service;

import delivery.model.entity.Order;

import java.util.List;

public interface OrderService {

    Order create(Order order);

    List<Order> findByUserId(long idUser);
}
