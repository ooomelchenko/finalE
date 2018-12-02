package delivery.model.entity;

public class Order {

    private long id;
    private Tariff tariff;
    private Route route;
    private Bill bill;
    private User user;

    public Order() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Tariff getTariff() {
        return tariff;
    }
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }

    public Bill getBill() {
        return bill;
    }
    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
