package delivery.model.entity;

import java.time.LocalDate;

public class Order {

    private long id;
    private Type type;
    private int weightGr;
    private LocalDate arrivalDate;
    private AvailableOption availableOption;
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

    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    public int getWeightGr() {
        return weightGr;
    }
    public void setWeightGr(int weightGr) {
        this.weightGr = weightGr;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public AvailableOption getAvailableOption() {
        return availableOption;
    }
    public void setAvailableOption(AvailableOption availableOption) {
        this.availableOption = availableOption;
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
