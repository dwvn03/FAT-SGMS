package controllers.student;

import data.StatusDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Session;
import models.Status;
import utils.date.DateUtils;

import java.io.IOException;

@WebServlet(name = "AttendanceReportDetailController", value = "/student/attendance-report/detail")
public class AttendanceReportDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("student-id"));
        int groupId = Integer.parseInt(request.getParameter("group-id"));

        StatusDataSource statusDataSource = new StatusDataSource();
        var statuses = statusDataSource.getStatusesFromStudentIdAndGroupId(studentId, groupId);

        int numberOfAbsent = 0;
        for (Status status : statuses) {
            Session session = status.getSession();
            session.setYetToStart(DateUtils.getYetToStart(session));

            if (!status.isAttended() && session.isYetToStart()) {
                numberOfAbsent++;
            }
        }

        float absentPercentage = (float) numberOfAbsent / statuses.size() * 100;

        request.setAttribute("statuses", statuses);
        request.setAttribute("numberOfAbsent", numberOfAbsent);
        request.setAttribute("absentPercentage", absentPercentage);

        request.setAttribute("location", "/student/attendance-report/detail");
        request.getRequestDispatcher("/WEB-INF/views/pages/client/student/attendance-report-detail.jsp").forward(request, response);
    }
}
