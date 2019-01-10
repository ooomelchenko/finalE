package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.controller.exceptions.NotEnoughMoneyException;
import delivery.controller.exceptions.SettleUpDuplicationException;
import delivery.model.entity.Bill;
import delivery.model.service.BillService;
import delivery.util.bundleManagers.ContentManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class BillPayAjaxCommand implements Command {

    private BillService billService;

    public BillPayAjaxCommand(BillService billService) {
        this.billService = billService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String lang = (String) request.getSession().getAttribute("lang");

        long billId = Long.parseLong(request.getParameter("billId"));

        Optional<Bill> bill = billService.getById(billId);

        bill.ifPresent(
                b -> {
                    try (PrintWriter writer = response.getWriter()) {
                        try {
                            if (billService.settleUp(b))
                                response.setStatus(200);
                        } catch (SettleUpDuplicationException e) {
                            writer.print(ContentManager.getProperty("exception.SettleUpDuplicationException", lang));
                            response.setStatus(500);
                        } catch (NotEnoughMoneyException e) {
                            writer.print(ContentManager.getProperty("exception.NotEnoughMoneyException", lang));
                            response.setStatus(500);
                        } catch (RuntimeException e) {
                            writer.print(ContentManager.getProperty("exception.runtime", lang));
                            response.setStatus(500);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        return null;
    }
}
