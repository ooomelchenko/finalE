package delivery.model.dao;

import delivery.model.dao.mapper.RouteMapper;
import delivery.model.entity.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {

    private Connection connection;

    RouteDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Route entity) {

    }

    @Override
    public Route findById(long id) {
        return null;
    }

    @Override
    public List<Route> findAll() {

        List<Route> routeList = new ArrayList<>();
        RouteMapper routeMapper = new RouteMapper();

        final String query = "select * from routes";

        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                routeList.add(routeMapper.extractFromResultSet(rs));
            }
            return routeList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Route entity) {

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
