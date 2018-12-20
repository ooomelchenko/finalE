package delivery.model.service;

import delivery.model.entity.Route;
import delivery.model.entity.Tariff;

import java.time.LocalDate;

public interface CalculatorService {
    int KG = 1000;

    long getDeliveryPrice(Tariff tariff, Route route, int weight);

    LocalDate getDeliveryDate(Tariff tariff, Route route);

}