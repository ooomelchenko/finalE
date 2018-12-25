package delivery.model.dao;

import delivery.model.entity.Bill;

public interface BillDao extends GenericDao<Bill> {
    boolean settleUp(Bill bill);
}
