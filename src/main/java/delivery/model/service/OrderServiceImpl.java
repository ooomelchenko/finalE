package delivery.model.service;

import delivery.model.dao.DaoFactoryAbst;
import delivery.model.dao.OrderDao;
import delivery.model.entity.Order;

public class OrderServiceImpl implements OrderService {

    private DaoFactoryAbst daoFactoryAbst = DaoFactoryAbst.getInstance();

    @Override
    public void create(Order order) {
        try (OrderDao dao = daoFactoryAbst.createOrderDao()) {
            dao.create(order);
        }
    }
}
