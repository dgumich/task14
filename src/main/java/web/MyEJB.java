package web;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Класс EJB с логикой обработки запроса
 */

@Stateless
public class MyEJB {

    /**
     * Метод принимает на вход:
     * @param req - http запрос от сервлета
     * @param resp - http ответ сервлета
     * @throws IOException
     * Из входящего http запроса берется название параметра окружения среды, которую необходимо вывести на экран.
     * Результат передается в http ответ сервлета
     */
    public void start(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String parName = req.getParameter("parametrname");
        resp.getWriter().write(getForServlets(parName));

    }


    /**
     * Метод получает на вход:
     * @param parametrName - имя параметра окружения среды, которая интересует пользователя и возвращает
     * @return String с адрес для данного параметра
     * Данный метод используется в сервлете
     */
    public String getForServlets(String parametrName) {

        // если выбраны все параметры, то создается Map, в который они выгружаются и затем с помощью StringBuilder
        // приводятся к строковому виду
        if ("all".equals(parametrName)) {
            Map<String, String> envVariable = System.getenv();
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> map : envVariable.entrySet()) {
                sb.append(map.getKey());
                sb.append(" = ");
                sb.append(map.getValue()).append("\n");
            }
            return sb.toString();
        }
        //во все остальных значениях возвращается название параметра и его значение
        return parametrName + " = "  + System.getenv(parametrName);


    }

    /**
     * Аналогично методу для сервлета, метод для JSP страницы так же получает на вход:
     * @param parametrName - имя параметра окружения среды и возвращает
     * @return String с адрес для данного параметра
     * В данном методе добавляются html теги для вывода значений в виде таблицы
     */
    public String getForJSP(String parametrName) {

        StringBuilder sb = new StringBuilder();
        Map<String, String> map = new HashMap<>();
        if ("all".equals(parametrName)) {
            map = System.getenv();
        } else {
            map.put(parametrName, System.getenv(parametrName));
        }
        sb.append("<table border=\"1\">\n");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append("<tr>\n");
            sb.append("<td>");
            sb.append(entry.getKey());
            sb.append("</td>\n");
            sb.append("<td>");
            sb.append(entry.getValue());
            sb.append("</td>\n");
            sb.append("</tr>\n");
        }

        sb.append("</table>");

        return sb.toString();


    }


}
