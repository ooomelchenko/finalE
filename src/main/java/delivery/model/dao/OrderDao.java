package delivery.model.dao;

import delivery.model.entity.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> findByUserId(long idUser);
}
