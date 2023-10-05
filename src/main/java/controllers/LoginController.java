package controllers;

import filter.AdminFilter;
import filter.ClientFilter;
import filter.StaffFilter;
import models.User;
import services.RoleService;
import services.UserService;
import utils.AuthUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name ="LoginController", value = "/login")
public class LoginController extends HttpServlet {
    private UserService userService;
    private RoleService roleService;
//    private AdminFilter adminFilter;
//    private ClientFilter clientFilter;
//    private StaffFilter staffFilter;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "logout":
                logout(req, resp);
                break;
            case "registerUser":
                registerUser(req, resp);
                break;
            case "forgotPassword":
                forgotPassword(req, resp);
                break;
            case "resetPassword":
                resetPassword(req, resp);
                break;
            default:
                showLogin(req, resp);
        }
    }

    private void registerUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login/register.jsp").forward(req, resp);
    }

    private void forgotPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login/forgotPassword.jsp").forward(req, resp);
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("login/login.jsp").forward(req, resp);
    }

    private void showLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "login":
                login(req, resp);
                break;
            case "register":
                register(req, resp);
            case "resetPassword":
                resetPassword(req, resp);
                break;
            case "restorePassword":
                restorePassword(req, resp);
                break;
        }
    }

    private void restorePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String password = req.getParameter("password");
        String re_password = req.getParameter("re_password");
        int id = Integer.parseInt(req.getParameter("id"));
        String pinCode = req.getParameter("pinCode");
        if((password.equals(re_password)) && pinCode.equals("111")){
            userService.updatePassword(id, password);
            resp.sendRedirect("/login?message=Restore Password success!");
        }else{
//            req.setAttribute("user", userService.getUserById(id));
//            req.setAttribute("message", "Password invalid!");
//            req.getRequestDispatcher("/login/resetPassword.jsp").forward(req, resp);
            resp.sendRedirect("/login?action=resetPassword&id="+id+"&message=Password invalid!");
        }
    }

    private void resetPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email ="";
        if(req.getParameter("id")==null){
            email = req.getParameter("email");
        }else email = userService.getEmailById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("user", userService.getUserByEmail(email));
        req.getRequestDispatcher("/login/resetPassword.jsp").forward(req, resp);
    }
    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String password = req.getParameter("confirmPassword");
        userService.register(fullName, userName, email, password);
        resp.sendRedirect("/login");

    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        if(userService.login(userName, password)){
            String userRole = userService.getUserByUserName(userName).getRole().getRoleName();
            HttpSession session = req.getSession();
            session.setAttribute("user", userService.getUserByUserName(userName));
            AuthUtils.init(req);
            switch (userRole) {
                case "Admin" -> {
                resp.sendRedirect("/admin?message= Login Success");
                }
                case "Client" -> {
                    resp.sendRedirect("/shopping?message= Login Success");
                }
                case "Staff" -> {
                    resp.sendRedirect("/product?message= Login Success");
                }
            }
        } else resp.sendRedirect("/login?message=Password or username is invalid");
    }

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        roleService = new RoleService();
    }
}
