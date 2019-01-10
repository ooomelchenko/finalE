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
    public Route create(Route route) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("route.create"), Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, route.getRouteStart());
            ps.setString(2, route.getRouteEnd());
            ps.setInt(3, route.getDistanceKm());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){
                route.setId(rs.getLong(1));
            }
            return route;
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public RouteLocale createWithLocalFields(RouteLocale routeLocalized){

        RouteLocale.LocalFields uaFields = routeLocalized.getLocalFieldsMap().get("ua");

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("route.create.withLocalFields"))){

            ps.setString(1, routeLocalized.getRoute().getRouteStart());
            ps.setString(2, routeLocalized.getRoute().getRouteEnd());
            ps.setInt(3, routeLocalized.getRoute().getDistanceKm());
            ps.setString(4, uaFields.getRouteStart());
            ps.setString(5, uaFields.getRouteEnd());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){
                routeLocalized.getRoute().setId(rs.getLong(1));
            }
            return routeLocalized;
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Route findById(long id) {

        RouteMapper routeMapper = new RouteMapper();

        try (PreparedStatement st = connection.prepareStatement(SqlQueryManager.getProperty("route.findById"))) {

            st.setLong(1, id);

            ResultSet rs = st.executeQuery();

            if(rs.next())
                return routeMapper.extractFromResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return null;
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Route route) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("route.update"))){

            ps.setString(1, route.getRouteStart());
            ps.setString(2, route.getRouteEnd());
            ps.setInt(3, route.getDistanceKm());

            return ps.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(long id) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("route.delete"))){

            ps.setLong(1, id);

            return ps.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
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
