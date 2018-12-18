package delivery.controller;

import delivery.controller.commands.ActionFactory;
import delivery.controller.commands.Command;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

@WebServlet(urlPatterns = "/delivery/*")
public class Servlet extends HttpServlet {
    //private final static Logger log = Logger.getLogger(Servlet.class);


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<String>());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
       // log.debug("processRequest");
        String page ;

        ActionFactory client = new ActionFactory();
        Command command = client.defineCommand(request);
        page = command.execute(request, response);
// метод возвращает страницу ответа
        if (page != null) {
            if(page.contains("redirect")){
                response.sendRedirect(page.replace(":redirect", ""));
            }else {
                request.getRequestDispatcher(page).forward(request, response);
            }
        }

    }
}