package delivery.model.entity;

public class Route {

    private long id;
    private String routeStart;
    private String routeEnd;
    private long distanceKm;

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

    public long getDistanceKm() {
        return distanceKm;
    }
    public void setDistanceKm(long distanceKm) {
        this.distanceKm = distanceKm;
    }

}