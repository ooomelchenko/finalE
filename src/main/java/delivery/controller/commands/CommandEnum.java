package delivery.controller.commands;

import delivery.controller.commands.actions.*;
import delivery.controller.exceptions.WrongCommandException;

public enum CommandEnum {

    PROFILE ("PROFILE", new ProfileCommand()) {},
    EMPTY ("EMPTY", new EmptyCommand()) {},
 //   GUEST ("GUEST", new GuestCommand()) {},
    LOGIN ("LOGIN", new LoginCommand()) {},
    REGISTRATION("REGISTRATION", new RegistrationCommand()){},
    LOGOUT ("LOGOUT", new LogoutCommand()) {},
    REGISTRATIONVALID("REGISTRATIONVALID", new RegistrationValidCommand()){},
    USER ("USER", new UserCommand()) {},
    ADMIN ("ADMIN", new AdminCommand()) {},
    ROUTES ("ROUTES", new RoutesCommand()) {},
    TARIFFS ("TARIFFS", new TariffsCommand()) {},
    CALCULATOR ("CALCULATOR", new CalculatorCommand()) {},
    CALCULATE ("CALCULATE", new CalculateDeliveryPriceCommand()) {},
    TARIFFSBYROUTE("GETTARIFFLISTBYROUTE", new GetTariffListByRouteCommand()) {},
    ORDERCREATOR("USER/ORDERCREATOR", new OrderCreatorCommand()),
    CREATEORDER("USER/CREATEORDER", new CreateOrderCommand()),
    ACCOUNTINVOICE("USER/ACCOUNT/INVOICE", new AccountInvoiceCommand()),
    ACCOUNTREFILL("USER/ACCOUNT/REFILL", new AccountRefillCommand()),
    BILLPAY("USER/BILL/PAY", new BillPayCommand()),
    ROUTESMANAGER ("ADMIN/ROUTES/MANAGER", new RoutesManagerCommand()) {},
    TARIFFEDIT ("ADMIN/TARIFF/EDIT", new TariffEditCommand()) {};

    private String commandPath;
    private Command command;

    CommandEnum(String commandPath, Command command){
        this.commandPath = commandPath;
        this.command = command;
    }

    public Command getCurrentCommand() {
        return command;
    }

    public static Command getByPath(String path) throws WrongCommandException {
        for (CommandEnum commandEnum : CommandEnum.values()) {
            if (commandEnum.commandPath.equalsIgnoreCase(path))
                return commandEnum.command;
        }
         throw new WrongCommandException(path);
    }

}
