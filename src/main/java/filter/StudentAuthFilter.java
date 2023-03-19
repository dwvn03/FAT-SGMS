package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.auth.AuthUtils;

import java.io.IOException;

@WebFilter(filterName = "StudentFilter", urlPatterns = {"/student/*"})
public class StudentAuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;

        if (!AuthUtils.isStudent(req.getSession()) || !AuthUtils.isAdmin(req.getSession())) {
            ((HttpServletResponse) response).sendError(401, "Unauthorized");
            return;
        }
        chain.doFilter(request, response);
    }
}
