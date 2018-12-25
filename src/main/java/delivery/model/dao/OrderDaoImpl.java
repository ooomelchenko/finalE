package delivery.model.dao;

import delivery.model.dao.mapper.OrderMapper;
import delivery.model.entity.Order;
import delivery.util.bundleManagers.SqlQueryManager;

import java.sql.*;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private Connection connection;

    OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Order order) {
        try(PreparedStatement stOrder = connection.prepareStatement(SqlQueryManager.getProperty("order.create"), Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stBill = connection.prepareStatement(SqlQueryManager.getProperty("bill.create"))){
            connection.setAutoCommit(false);

            stOrder.setString(1, order.getType().name());
            stOrder.setInt(2, order.getWeightGr());
            stOrder.setDate(3, Date.valueOf(order.getArrivalDate()));
            stOrder.setLong(4, order.getAvailableOption().getId());
            stOrder.setLong(5, order.getUser().getId());

            stOrder.execute();

            ResultSet rs = stOrder.getGeneratedKeys();
            rs.next();
            long orderId = rs.getLong(1);

            stBill.setLong(1, order.getBill().getTotal());
            stBill.setBoolean(2, order.getBill().isPaid());
            stBill.setLong(3, order.getUser().getId());
            stBill.setLong(4, orderId);

            stBill.execute();

            connection.commit();

        }
        catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
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
