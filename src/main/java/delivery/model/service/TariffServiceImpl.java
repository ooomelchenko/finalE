package delivery.model.service;

import delivery.model.dao.DaoFactoryAbst;
import delivery.model.dao.TariffDao;
import delivery.model.entity.Tariff;

import java.util.List;
import java.util.Optional;

public class TariffServiceImpl implements TariffService {

    private DaoFactoryAbst daoFactoryAbst = DaoFactoryAbst.getInstance();

    @Override
    public List<Tariff> getAllTariffs() {
        try (TariffDao dao = daoFactoryAbst.createTariffDao()) {
            return dao.findAll();
        }
    }

    @Override
    public Optional<Tariff> getTariff(long id) {
        try (TariffDao dao = daoFactoryAbst.createTariffDao()) {
            return Optional.ofNullable(dao.findById(id));
        }
    }

    @Override
    public Tariff create(Tariff tariff) {
        try (TariffDao dao = daoFactoryAbst.createTariffDao()) {
           return dao.create(tariff);
        }
    }

    @Override
    public List<Tariff> findAvailableByRouteId(long routeId) {
        try (TariffDao dao = daoFactoryAbst.createTariffDao()) {
            return dao.findAvailableByRouteId(routeId);
        }
    }

    @Override
    public boolean update(Tariff tariff){
        try (TariffDao dao = daoFactoryAbst.createTariffDao()) {
            return dao.update(tariff);
        }
    }
}
