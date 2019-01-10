package delivery.model.service;

import delivery.model.dao.DaoFactoryAbst;
import delivery.model.dao.OrderDao;
import delivery.model.entity.Order;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private DaoFactoryAbst daoFactoryAbst = DaoFactoryAbst.getInstance();

    @Override
    public Order create(Order order) {
        try (OrderDao dao = daoFactoryAbst.createOrderDao()) {
           return dao.create(order);
        }
    }
    @Override
    public List<Order> findByUserId(long idUser) {
        try (OrderDao dao = daoFactoryAbst.createOrderDao()) {
           return dao.findByUserId(idUser);
        }
    }
}
