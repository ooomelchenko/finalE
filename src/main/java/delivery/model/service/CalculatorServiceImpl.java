package delivery.model.service;

import delivery.model.dao.DaoFactoryAbst;
import delivery.model.entity.Route;
import delivery.model.entity.Tariff;

import java.time.LocalDate;

public class CalculatorServiceImpl implements CalculatorService {

    private DaoFactoryAbst daoFactoryAbst = DaoFactoryAbst.getInstance();

    @Override
    public long getDeliveryPrice(Tariff tariff, Route route, int weightGr) {
        return (tariff.getCostPerKg()*weightGr)/KG + route.getDistanceKm()*tariff.getCostPerKm();
    }

    @Override
    public LocalDate getDeliveryDate(Tariff tariff, Route route) {
        return LocalDate.now().plusDays(route.getDistanceKm()/tariff.getPaceDayKm());
    }

}
