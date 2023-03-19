package controllers.instructor;

import data.StatusDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Status;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "TakeAttendanceController", value = "/instructor/take-attendance")
public class TakeAttendanceController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sessionId = Integer.parseInt(request.getParameter("session_id"));

        StatusDataSource statusDataSource = new StatusDataSource();
        List<Status> statuses = statusDataSource.getStatusesBySessionId(sessionId);
        request.setAttribute("statuses", statuses);
        request.setAttribute("session_id", sessionId);
        request.setAttribute("location", "/instructor/take-attendance");
        request.getRequestDispatcher("/WEB-INF/views/pages/client/instructor/take-attendance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sessionId = Integer.parseInt(request.getParameter("session_id"));

        String[] studentIds = request.getParameterValues("student_id");
        String[] attended = request.getParameterValues("attended");

        StatusDataSource statusDataSource = new StatusDataSource();
        boolean succeeded = statusDataSource.updateStatuses(sessionId, studentIds, attended);

        response.sendRedirect("/instructor/schedule?succeeded=" + succeeded);
    }
}
