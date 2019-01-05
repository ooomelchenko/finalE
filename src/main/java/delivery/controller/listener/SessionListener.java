package delivery.controller.listener;

import delivery.model.entity.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        Map<String, HttpSession> loggedUsers = (ConcurrentHashMap<String, HttpSession>) httpSessionEvent.getSession().getServletContext().getAttribute("loggedUsers");

        User user = (User) httpSessionEvent.getSession().getAttribute("user");

        loggedUsers.remove(user.getLogin());

        httpSessionEvent.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
    }
}
