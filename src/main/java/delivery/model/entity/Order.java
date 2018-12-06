package delivery.model.entity;

import java.sql.Date;

public class Order {

    private long id;
    private Type type;
    private int weight;
    private Date dateReceiving;
    private Tariff tariff;
    private Route route;
    private Bill bill;
    private User user;

    public enum Type {MAIL, PARCEL, PACKAGE, CARGO}

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

    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getDateReceiving() {
        return dateReceiving;
    }
    public void setDateReceiving(Date dateReceiving) {
        this.dateReceiving = dateReceiving;
    }
}
