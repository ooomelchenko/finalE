package delivery.model.dao;

import delivery.model.entity.Tariff;

import java.util.List;

public interface TariffDao extends GenericDao<Tariff> {

    List<Tariff> findAvailableByRouteId(long routeId);
}
