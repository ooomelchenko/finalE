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
    CALCULATE ("CALCULATE", new CalculateDeliveryPriceCommand()) {};

    /*

    SORTBYPRICE ("ADMIN", new AdminCommand()) {},
    FILTERORDERS ("ADMIN", new AdminCommand()){};
    */
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
