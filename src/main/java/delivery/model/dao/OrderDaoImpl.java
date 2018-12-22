package delivery.model.dao;

import delivery.model.dao.mapper.OrderMapper;
import delivery.model.entity.Order;
import delivery.util.bundleManagers.SqlQueryManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private Connection connection;

    OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Order entity) {

    }

    @Override
    public Order findById(long id) {

        try (PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("order.findById"))) {

            ps.setLong(1, id);
            ResultSet rs;
            rs = ps.executeQuery();
            OrderMapper mapper = new OrderMapper();
            if (rs.next()) {
                return mapper.extractFromResultSet(rs);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
