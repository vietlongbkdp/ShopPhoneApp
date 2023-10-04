package utils;

import models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthUtils {
    private static HttpServletRequest request;

    public static void init(HttpServletRequest request){
        AuthUtils.request  = request;
    }

    public static User getUser(){
        HttpSession session= request.getSession();
        User user=(User) session.getAttribute("user");
        return user;
    }
}
