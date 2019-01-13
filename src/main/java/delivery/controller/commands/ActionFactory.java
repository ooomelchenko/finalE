package delivery.controller.commands;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private static final Logger log = Logger.getLogger(ActionFactory.class);

    public Command defineCommand(HttpServletRequest request) {

        String path = request.getRequestURI();

        log.info("request_path ="+path);

        path = path.replaceAll(".*/delivery/" , "").replaceAll(".*/delivery" , "");

        if ( path.isEmpty()) {
            return CommandEnum.EMPTY.getCurrentCommand();
        }
        else
            return CommandEnum.getByPath(path.toUpperCase());
    }

}