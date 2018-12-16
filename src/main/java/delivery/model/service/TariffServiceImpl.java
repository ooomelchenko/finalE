package delivery.model.service;

import delivery.model.dao.DaoFactoryAbst;
import delivery.model.dao.FactoryDaoAbst;
import delivery.model.dao.TariffDao;
import delivery.model.entity.Tariff;

import java.util.List;
import java.util.Optional;

public class TariffServiceImpl implements TariffService {

    private DaoFactoryAbst daoFactoryAbst = FactoryDaoAbst.getInstance();

    @Override
    public List<Tariff> getAllTariffs() {
        try (TariffDao dao = daoFactoryAbst.createTariffDao()) {
            return dao.findAll();
        }
    }

    @Override
    public Optional<Tariff> getTariff(long id) {
        return Optional.empty();
    }

    @Override
    public Tariff create(Tariff tariff) {
        return null;
    }
}
