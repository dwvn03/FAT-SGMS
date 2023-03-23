package controllers.instructor;

import data.GroupDataSource;
import data.StatusDataSource;
import data.StudentDataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Session;
import models.Status;
import utils.date.DateUtils;

import java.io.IOException;

@WebServlet(name = "InstructorAttendanceReportDetailController", value = "/instructor/attendance-report/detail")
public class AttendanceReportDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("group-id"));

        StudentDataSource studentDataSource = new StudentDataSource();
        var students = studentDataSource.getStudentsByGroupId(groupId);

        request.setAttribute("students", students);

        request.setAttribute("location", "/instructor/attendance-report/detail");
        request.getRequestDispatcher("/WEB-INF/views/pages/client/instructor/attendance-report-detail.jsp").forward(request, response);
    }
}
