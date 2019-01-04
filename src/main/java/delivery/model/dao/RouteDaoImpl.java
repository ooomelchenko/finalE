package delivery.model.dao;

import delivery.model.dao.mapper.RouteMapper;
import delivery.model.dao.mapper.TariffMapper;
import delivery.model.dto.RouteLocale;
import delivery.model.entity.Route;
import delivery.model.entity.Tariff;
import delivery.util.bundleManagers.SqlQueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteDaoImpl implements RouteDao {

    private Connection connection;

    RouteDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Route route) {

        try(PreparedStatement st = connection.prepareStatement(SqlQueryManager.getProperty("route.create"))){

            st.setString(1, route.getRouteStart());
            st.setString(2, route.getRouteEnd());
            st.setInt(3, route.getDistanceKm());

            st.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void createWithLocalFields(RouteLocale routeLocale){

        RouteLocale.LocalFields uaFields = routeLocale.getLocalFieldsMap().get("ua");

        try(PreparedStatement st = connection.prepareStatement(SqlQueryManager.getProperty("route.create.withLocalFields"))){

            st.setString(1, routeLocale.getRoute().getRouteStart());
            st.setString(2, routeLocale.getRoute().getRouteEnd());
            st.setInt(3, routeLocale.getRoute().getDistanceKm());
            st.setString(4, uaFields.getRouteStart());
            st.setString(5, uaFields.getRouteEnd());
            st.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Route findById(long id) {

        RouteMapper routeMapper = new RouteMapper();

        try (PreparedStatement st = connection.prepareStatement(SqlQueryManager.getProperty("route.findById"))) {

            st.setLong(1, id);

            ResultSet rs = st.executeQuery();
            rs.next();
            return routeMapper.extractFromResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Route> findAll() {

        Map<Long, Route> routeMap = new HashMap<>();
        Map<Long, Tariff> tariffMap = new HashMap<>();

        RouteMapper routeMapper = new RouteMapper();
        TariffMapper tariffMapper = new TariffMapper();

        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(SqlQueryManager.getProperty("route.findAll"));

            while (rs.next()) {

                Route route = routeMapper.makeUnique(routeMap, routeMapper.extractFromResultSet(rs));

                Tariff tariff = tariffMapper.makeUnique(tariffMap, tariffMapper.extractFromResultSet(rs));

                route.getTariffList().add(tariff);

            }
            return new ArrayList<>(routeMap.values());
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
