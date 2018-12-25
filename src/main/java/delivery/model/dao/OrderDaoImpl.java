package delivery.model.dao;

import delivery.model.dao.mapper.*;
import delivery.model.entity.*;
import delivery.util.bundleManagers.SqlQueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        OrderMapper mapper = new OrderMapper();

        try (PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("order.findById"))) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

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
    public List<Order> findByUserId(long idUser) {

        Map<Long, Order> orderMap = new HashMap<>();

        OrderMapper orderMapper = new OrderMapper();
        BillMapper billMapper = new BillMapper();
        AvailableOptionMapper availableOptionMapper = new AvailableOptionMapper();
        RouteMapper routeMapper = new RouteMapper();
        TariffMapper tariffMapper = new TariffMapper();

        try (PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("order.findByUserId"))) {

            ps.setLong(1, idUser);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = orderMapper.extractFromResultSet(rs);
                Bill bill = billMapper.extractFromResultSet(rs);
                AvailableOption availableOption = availableOptionMapper.extractFromResultSet(rs);
                Route route = routeMapper.extractFromResultSet(rs);
                Tariff tariff = tariffMapper.extractFromResultSet(rs);

                availableOption.setTariff(tariff);
                availableOption.setRoute(route);
                bill.setOrder(order);
                order.setAvailableOption(availableOption);
                order.setBill(bill);

                orderMapper.makeUnique(orderMap, order);

            }

            return new ArrayList<>(orderMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
