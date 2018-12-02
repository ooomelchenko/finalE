package delivery.controller;

import delivery.controller.commands.ActionFactory;
import delivery.controller.commands.Command;
import delivery.controller.commands.MessageManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Servlet extends HttpServlet {

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

        String page ;

        ActionFactory client = new ActionFactory();
        Command command = client.defineCommand(request);
        page = command.execute(request);
// метод возвращает страницу ответа
        if (page != null) {
            if(page.contains("redirect")){
                response.sendRedirect(page.replace(":redirect", ""));
            }else {
                request.getRequestDispatcher(page).forward(request, response);
            }
        }
        else {
// установка страницы c cообщением об ошибке
            System.out.println("page = \"Error.jsp\";");
            page = "Error.jsp";
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}