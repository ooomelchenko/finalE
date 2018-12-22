package delivery.model.dao.mapper;

import delivery.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderMapper implements ObjectMapper<Order> {

    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id_order"));
        order.setWeight(rs.getInt("weight"));
        order.setArrivalDate(rs.getDate("arrivaldate").toLocalDate());
        return order;
    }

    @Override
    public Order makeUnique(Map<Long, Order> cache, Order order) {
        cache.putIfAbsent(order.getId(), order);
        return cache.get(order.getId());
    }
}