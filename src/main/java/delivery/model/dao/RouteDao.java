package delivery.model.dao;

import delivery.model.dto.RouteLocale;
import delivery.model.entity.Route;

public interface RouteDao extends GenericDao<Route> {
    void createWithLocalFields(RouteLocale routeLocale);
}
