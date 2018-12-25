package delivery.model.service;

import delivery.model.dao.DaoFactoryAbst;
import delivery.model.dao.RouteDao;
import delivery.model.dto.RouteLocale;
import delivery.model.entity.Route;

import java.util.List;
import java.util.Optional;

public class RouteServiceImpl implements RouteService {

    private DaoFactoryAbst daoFactoryAbst = DaoFactoryAbst.getInstance();

    @Override
    public List<Route> getAllRoutes() {
        try (RouteDao dao = daoFactoryAbst.createRouteDao()) {
            return dao.findAll();
        }
    }

    @Override
    public Optional<Route> getRoute(long id) {
        try (RouteDao dao = daoFactoryAbst.createRouteDao()) {
            return Optional.ofNullable(dao.findById(id));
        }
    }

    @Override
    public void create(Route route) {
        try (RouteDao dao = daoFactoryAbst.createRouteDao()) {
            dao.create(route);
        }
    }

    @Override
    public void createWithLocalFields(RouteLocale routeLocale){
        try (RouteDao dao = daoFactoryAbst.createRouteDao()) {
             dao.createWithLocalFields(routeLocale);
        }
    }
}