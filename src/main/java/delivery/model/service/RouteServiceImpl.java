package delivery.model.service;

import delivery.model.dao.DaoFactoryAbst;
import delivery.model.dao.FactoryDaoAbst;
import delivery.model.dao.RouteDao;
import delivery.model.entity.Route;
import java.util.List;
import java.util.Optional;

public class RouteServiceImpl implements RouteService {

    private DaoFactoryAbst daoFactoryAbst = FactoryDaoAbst.getInstance();

    @Override
    public List<Route> getAllRoutes() {
        try (RouteDao dao = daoFactoryAbst.createRouteDao()) {
            return dao.findAll();
        }
    }

    @Override
    public Optional<Route> getRoute(long id) {
        return Optional.empty();
    }

    @Override
    public Route create(Route route) {
        return null;
    }
}
