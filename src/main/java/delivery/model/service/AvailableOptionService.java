package delivery.model.service;

import delivery.model.entity.AvailableOption;

import java.util.List;
import java.util.Optional;

public interface AvailableOptionService {
    List<AvailableOption> getAllAvailableOptions();

    Optional<AvailableOption> getAvailableOption(long id);

    AvailableOption create(AvailableOption availableOption);

    Optional<AvailableOption> getByRouteTariffId(long id_route, long id_tariff);

    boolean updateOrInsert(List<AvailableOption> optionList);
}
