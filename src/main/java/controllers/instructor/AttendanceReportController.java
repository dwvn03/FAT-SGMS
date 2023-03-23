package controllers.instructor;

import data.GroupDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "InstructorAttendanceReportController", value = "/instructor/attendance-report")
public class AttendanceReportController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int instructorId = (int) request.getSession().getAttribute("userId");

        GroupDataSource groupDataSource = new GroupDataSource();
        var groups = groupDataSource.getGroupsByInstructorId(instructorId);
        request.setAttribute("groups", groups);
        request.setAttribute("location", "/instructor/attendance-report");
        request.getRequestDispatcher("/WEB-INF/views/pages/client/instructor/attendance-report.jsp").forward(request, response);
    }
}
