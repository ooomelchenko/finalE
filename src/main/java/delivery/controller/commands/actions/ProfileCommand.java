package delivery.controller.commands.actions;

import delivery.controller.commands.Command;
import delivery.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ProfileCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = Optional.ofNullable(((User) request.getSession().getAttribute("user"))).orElse(new User());

        return user.getRole().getPath();
    }
}
