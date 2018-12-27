package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.Bill;
import delivery.model.service.BillService;
import delivery.model.service.BillServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class BillPayCommand implements Command {

    private BillService billService = new BillServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        long billId = Long.parseLong(request.getParameter("billId"));

        Optional<Bill> bill = billService.getById(billId);

        if (bill.isPresent() && billService.settleUp(bill.get())){
            response.setStatus(200);
        }
        else response.setStatus(500);

        return null;
    }
}
