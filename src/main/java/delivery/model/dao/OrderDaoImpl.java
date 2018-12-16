package delivery.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private Connection connection;

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Object entity) {

    }

    @Override
    public Object findById(long id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(Object entity) {

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
