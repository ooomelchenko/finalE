package delivery.model.entity;

import java.sql.Date;

public class Order {

    private long id;
    private Type type;
    private int weight;
    private Date arrivalDate;
    private AvailableOption availableOption;
    private Bill bill;

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

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }
    public void setArrivalDate(Date arrivalDate) {
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
}
