package delivery.model.entity;

public class Bill {

    private long id;
    private long total;
    private boolean isPaid;

    public Bill() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }

    public boolean isPaid() {
        return isPaid;
    }
    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
