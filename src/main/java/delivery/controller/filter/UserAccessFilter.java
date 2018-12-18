package delivery.controller.filter;

import delivery.controller.exceptions.RoleAccessDeniedCommandException;
import delivery.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "UserAccessFilter", urlPatterns = {"/delivery/user/*"})
public class UserAccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException, RoleAccessDeniedCommandException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();

        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getRole().name().equals(User.Role.USER.name())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            throw new RoleAccessDeniedCommandException(path);
        }
    }

    @Override
    public void destroy() {
    }
}
