package delivery.model.service;

import delivery.model.entity.Bill;

import java.util.Optional;

public interface BillService {
    boolean settleUp(Bill bill);

    Optional<Bill> getById(long id);
}
