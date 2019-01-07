package delivery.model.service;

import delivery.model.entity.Order;

import java.util.List;

public interface OrderService {

    long create(Order order);

    List<Order> findByUserId(long idUser);
}
