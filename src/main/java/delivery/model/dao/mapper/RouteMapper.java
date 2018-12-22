package delivery.model.dao.mapper;

import delivery.model.entity.Route;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RouteMapper implements ObjectMapper<Route> {

    @Override
    public Route extractFromResultSet(ResultSet rs) throws SQLException {
        Route route = new Route();
        route.setId(rs.getLong("id_route"));
        route.setRouteStart(rs.getString("route_start"));
        route.setRouteEnd(rs.getString("route_end"));
        route.setDistanceKm(rs.getInt("distance_km"));
        return route;
    }

    @Override
    public Route makeUnique(Map<Long, Route> cache,
                           Route route) {
        cache.putIfAbsent(route.getId(), route);
        return cache.get(route.getId());
    }
}
