package utils.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;

public class AuthUtils {
    public static String getRole(HttpSession session) {
        return (String) session.getAttribute("role");
    }
    public static boolean isAuthorized(HttpSession session) {
        return session.getAttribute("role") != null && session.getAttribute("email") != null;
    }

    public static String readCookie(String key, HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(c -> key.equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst().orElse(null);
    }
}
