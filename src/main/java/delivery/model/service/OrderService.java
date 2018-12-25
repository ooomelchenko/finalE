package delivery.model.service;

import delivery.model.entity.Order;

import java.util.List;

public interface OrderService {

    void create(Order order);

    List<Order> findByUserId(long idUser);
}
