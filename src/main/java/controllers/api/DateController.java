package controllers.api;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.date.DateUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DateController", value = "/api/date")
public class DateController extends HttpServlet {
//    private final Gson gson = new Gson();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String year = request.getParameter("year");

        List<List<String>> mondaysAndSundays = DateUtils.getAllMondaysAndSundayInYear(Integer.parseInt(year));
//        String json = gson.toJson(mondaysAndSundays);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(mondaysAndSundays);
        out.flush();
    }
}
