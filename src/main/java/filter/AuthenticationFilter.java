package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.auth.Auth;

import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/dashboard", "/admin/*"})
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;

        if (!Auth.isAuthorized(req.getSession())) {
            ((HttpServletResponse) response).sendRedirect("/");
            return;
        }

        chain.doFilter(request, response);
    }
}
