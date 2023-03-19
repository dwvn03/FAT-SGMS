package controllers;

import data.StudentDataSource;
import data.UserDataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Student;
import models.User;
import utils.auth.GoogleAuth;

import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = "/auth/google")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String credential = request.getParameter("credential");
        String email = GoogleAuth.verifyGoogleCredential(credential);

        if (email == null) {
            response.sendError(401, "Invalid credential");
            return;
        }

        UserDataSource userDataSource = new UserDataSource();
        User user = userDataSource.get(email);

        if (user == null) {
            response.sendError(401, "User not found");
            return;
        }

        HttpSession session = request.getSession();

        if (user.getRole().equals("student")) {
            StudentDataSource studentDataSource = new StudentDataSource();

            Student student = studentDataSource.getStudentByUserId(user.getId());
            if (student == null) {
                response.sendError(401, "Student not found");
                return;
            }
            user.setStudent(student);
            session.setAttribute("studentId", student.getId());
        }

        session.setAttribute("email", email);
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole());
        session.setAttribute("name", user.getName());
        session.setAttribute("avatar", user.getAvatar());

        response.sendRedirect("/dashboard");
    }
}
