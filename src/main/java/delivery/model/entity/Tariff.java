package delivery.model.entity;

public class Tariff {

    private long id;
    private String name;
    private long costPerKm;
    private long costPerKg;

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
}
