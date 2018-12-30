package delivery.model.entity;

import java.io.Serializable;
import java.util.List;

public class Tariff implements Serializable {

    private long id;
    private String name;
    private long costPerKm;
    private long costPerKg;
    private int paceDayKm;

    private List<Route> routeList;

    public Tariff() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public long getCostPerKm() {
        return costPerKm;
    }
    public void setCostPerKm(long costPerKm) {
        this.costPerKm = costPerKm;
    }

    public long getCostPerKg() {
        return costPerKg;
    }
    public void setCostPerKg(long costPerKg) {
        this.costPerKg = costPerKg;
    }

    public int getPaceDayKm() {
        return paceDayKm;
    }
    public void setPaceDayKm(int paceDayKm) {
        this.paceDayKm = paceDayKm;
    }

    public List<Route> getRouteList() {
        return routeList;
    }
    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }
}
