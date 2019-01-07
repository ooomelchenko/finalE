package delivery.model.service;

import delivery.model.dto.RouteLocale;
import delivery.model.entity.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    List<Route> getAllRoutes();

    Optional<Route> getRoute(long id);

    long create(Route route);

    long createWithLocalFields(RouteLocale routeLocale);
}
