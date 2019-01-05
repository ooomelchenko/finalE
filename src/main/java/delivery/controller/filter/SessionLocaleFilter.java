package delivery.controller.filter;

import delivery.util.LocaleThreadLocal;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String locale= req.getParameter("sessionLocale");

        if (locale != null) {
            req.getSession().setAttribute("lang", locale);
            LocaleThreadLocal.setLocale(new Locale(locale));
        }
        else {
            try {
                LocaleThreadLocal.setLocale(new Locale((String) req.getSession().getAttribute("lang")));
            } catch (NullPointerException e) {
                LocaleThreadLocal.setLocale(Locale.getDefault());
            }
        }
        chain.doFilter(request, response);
    }

    public void destroy() {}

}
