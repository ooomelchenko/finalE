package delivery.model.service;

import delivery.model.entity.Route;
import delivery.model.entity.Tariff;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class CalculatorServiceTest {

    private CalculatorService calculatorService;
    private Tariff tariff;
    private Route route;
    private int weightGr;

    @Before
    public void beforeTests() {

        calculatorService = new CalculatorServiceImpl();
        tariff = new Tariff();
        route = new Route();

        tariff.setPaceDayKm(500);
        tariff.setCostPerKg(100L);
        tariff.setCostPerKm(200L);

        route.setDistanceKm(1000);

        weightGr = 50000;
    }

    @Test
    public void costForDistanceTest() {

        long cost = calculatorService.getDeliveryPrice(tariff, route, weightGr);

        Assert.assertEquals(cost, 205000);
    }

    @Test
    public void getDeliveryDateTest() {

        LocalDate deliveryDate = calculatorService.getDeliveryDate(tariff, route);

        Assert.assertEquals(LocalDate.now().plusDays(2), deliveryDate);
    }

}
