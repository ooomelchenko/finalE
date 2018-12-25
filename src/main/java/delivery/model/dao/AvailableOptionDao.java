package delivery.model.dao;

import delivery.model.entity.AvailableOption;

public interface AvailableOptionDao extends GenericDao<AvailableOption> {

    AvailableOption findByRouteTariffId(long id_route, long id_tariff);
}
