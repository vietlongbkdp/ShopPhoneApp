package utils;

import models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthUtils {
    private static HttpServletRequest request;

    public static void init(HttpServletRequest request) {
        AuthUtils.request = request;
    }

    public static User getUser() {
        User user = new User();
        if (request.getSession() != null) {
            HttpSession session = request.getSession();
            user = (User) session.getAttribute("user");
        } else if (request.getSession() != null) {
            user = null;
        }
        return user;
    }
}
