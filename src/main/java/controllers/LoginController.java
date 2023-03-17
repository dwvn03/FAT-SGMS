package controllers;

import data.UserDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.User;
import utils.auth.GoogleAuth;

import java.io.*;

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
        session.setAttribute("email", email);
        session.setAttribute("role", user.getRole());

        response.sendRedirect("/dashboard");
    }
}
