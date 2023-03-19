package controllers.student;

import data.SessionDataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Session;
import utils.date.DateUtils;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "ScheduleController", value = "/student/schedule")
public class ScheduleController extends HttpServlet {
    private final int START_YEAR = 2019;
    private final int END_YEAR = 2024;
    void processRequest(HttpServletRequest request, HttpServletResponse response, Date from) throws ServletException, IOException {
        int studentId = (int) request.getSession().getAttribute("studentId");
        SessionDataSource sessionDataSource = new SessionDataSource();
        List<Session> sessions = sessionDataSource.getSchedule(studentId, from, Date.valueOf(from.toLocalDate().plusDays(6)));

        var weeklySchedule = DateUtils.sessionListToWeeklySchedule(sessions);

        List<List<List<String>>> mondaysAndSundays = Stream.iterate(START_YEAR, i -> i + 1)
                .limit(END_YEAR - START_YEAR + 1)
                .map(year -> DateUtils.getAllMondaysAndSundayInYear(year))
                .collect(Collectors.toList());
        int fromYearIndex = from.toLocalDate().getYear() - START_YEAR;
        request.setAttribute("mondaysAndSundays", mondaysAndSundays);
        request.setAttribute("fromYearIndex", fromYearIndex);
        request.setAttribute("START_YEAR", START_YEAR);

        request.setAttribute("weeklySchedule", weeklySchedule);
        request.setAttribute("location", "/student/schedule");

        request.setAttribute("from", from);
        request.getRequestDispatcher("/WEB-INF/views/pages/client/student/weekly-schedule.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate from = DateUtils.getCurrentFirstDayOfWeek();
        processRequest(request, response, Date.valueOf(from));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String year = request.getParameter("year");
        String fromDate = request.getParameter("fromDate");
        String[] dateAndMonth = fromDate.split("/");

        LocalDate date = LocalDate.of(Integer.parseInt(year.trim()), Integer.parseInt(dateAndMonth[1].trim()), Integer.parseInt(dateAndMonth[0].trim()));

        processRequest(request, response, Date.valueOf(date));
    }
}
