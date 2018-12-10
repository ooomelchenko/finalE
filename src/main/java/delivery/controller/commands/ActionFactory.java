package delivery.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    public Command defineCommand(HttpServletRequest request) {

        String path = request.getRequestURI();
        path = path.replaceAll(".*/delivery/" , "");
        path = path.replaceAll(".*/delivery" , "");

        System.out.println(path);

        if ( path.isEmpty()) {
            return CommandEnum.EMPTY.getCurrentCommand();
        }
        else
            return CommandEnum.getByPath(path.toUpperCase());
    }

}