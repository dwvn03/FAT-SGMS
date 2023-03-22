package controllers.student;

import data.GroupDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "StudentAttendanceReportController", value = "/student/attendance-report")
public class AttendanceReportController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("student-id"));

        GroupDataSource groupDataSource = new GroupDataSource();
        var groups = groupDataSource.getGroupsByStudentId(studentId);
        request.setAttribute("groups", groups);

        request.setAttribute("location", "/student/attendance-report");
        request.getRequestDispatcher("/WEB-INF/views/pages/client/student/attendance-report.jsp").forward(request, response);
    }
}
