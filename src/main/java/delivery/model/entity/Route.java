package delivery.model.entity;

import java.util.List;

public class Route {

    private long id;
    private String routeStart;
    private String routeEnd;
    private int distanceKm;

    private List<Tariff> tariffList;

    public Route() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getRouteStart() {
        return routeStart;
    }
    public void setRouteStart(String routeStart) {
        this.routeStart = routeStart;
    }

    public String getRouteEnd() {
        return routeEnd;
    }
    public void setRouteEnd(String routeEnd) {
        this.routeEnd = routeEnd;
    }

    public int getDistanceKm() {
        return distanceKm;
    }
    public void setDistanceKm(int distanceKm) {
        this.distanceKm = distanceKm;
    }

    public List<Tariff> getTariffList() {
        return tariffList;
    }
    public void setTariffList(List<Tariff> tariffList) {
        this.tariffList = tariffList;
    }
}