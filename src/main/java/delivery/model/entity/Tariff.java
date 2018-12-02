package delivery.model.entity;

public class Tariff {

    private long id;
    private String name;
    private long costPerKm;

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

}
