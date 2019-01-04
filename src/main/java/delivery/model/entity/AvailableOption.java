package delivery.model.entity;

public class AvailableOption {

    private long id;
    private boolean isAvailable;
    private Route route;
    private Tariff tariff;

    public AvailableOption() {
    }

    public AvailableOption(Route route, Tariff tariff, boolean isAvailable) {
        this.isAvailable = isAvailable;
        this.route = route;
        this.tariff = tariff;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }

    public Tariff getTariff() {
        return tariff;
    }
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }
}
