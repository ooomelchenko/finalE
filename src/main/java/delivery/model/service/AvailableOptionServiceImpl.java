package delivery.model.service;

import delivery.model.dao.AvailableOptionDao;
import delivery.model.dao.DaoFactoryAbst;
import delivery.model.entity.AvailableOption;

import java.util.List;
import java.util.Optional;

public class AvailableOptionServiceImpl implements AvailableOptionService {

    private DaoFactoryAbst daoFactoryAbst = DaoFactoryAbst.getInstance();

    @Override
    public List<AvailableOption> getAllAvailableOptions() {
        try (AvailableOptionDao dao = daoFactoryAbst.createOptionDao()) {
            return dao.findAll();
        }
    }

    @Override
    public Optional<AvailableOption> getAvailableOption(long id) {
        try (AvailableOptionDao dao = daoFactoryAbst.createOptionDao()) {
            return Optional.ofNullable(dao.findById(id));
        }
    }

    @Override
    public void create(AvailableOption availableOption) {
        try (AvailableOptionDao dao = daoFactoryAbst.createOptionDao()) {
            dao.create(availableOption);
        }
    }

    @Override
    public Optional<AvailableOption> getByRouteTariffId(long id_route, long id_tariff) {
        try (AvailableOptionDao dao = daoFactoryAbst.createOptionDao()) {
            return Optional.ofNullable(dao.findByRouteTariffId(id_route, id_tariff));
        }
    }

    @Override
    public int updateOrInsert(List<AvailableOption> optionList){
        try (AvailableOptionDao dao = daoFactoryAbst.createOptionDao()) {
            return dao.updateOrInsert(optionList);
        }
    }
}
