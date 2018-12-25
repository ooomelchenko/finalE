package delivery.model.service;

import delivery.model.entity.Tariff;

import java.util.List;
import java.util.Optional;

public interface TariffService {

    List<Tariff> getAllTariffs();

    Optional<Tariff> getTariff(long id);

    void create(Tariff tariff);

    List<Tariff> findAvailableByRouteId(long routeId);
}
