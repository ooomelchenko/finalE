package delivery.model.dao;

import delivery.model.entity.AvailableOption;

import java.util.List;

public interface AvailableOptionDao extends GenericDao<AvailableOption> {

    boolean updateOrInsert(List<AvailableOption> optionList);

    AvailableOption findByRouteTariffId(long id_route, long id_tariff);
}
