package web;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс реализация сервлета с подключением EJB.
 */

@WebServlet("/servlet")
public class MyServlet extends HttpServlet {

    @EJB
    MyEJB myEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        myEJB.start(req, resp);
    }
}
