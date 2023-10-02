package controllers;

import filter.AdminFilter;
import filter.ClientFilter;
import filter.StaffFilter;
import services.RoleService;
import services.UserService;

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
//            case "create":
//                showCreate(req, resp);
//                break;
            default:
                showLogin(req, resp);
        }
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
                break;
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) {
        r
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        if(userService.login(userName, password)){
            String userRole = userService.getUserByUserName(userName).getRole().getRoleName();
            HttpSession session = req.getSession();
            session.setAttribute("userName", userName);
            switch (userRole) {
                case "Admin" -> {
                    req.getRequestDispatcher("user/admin/admin.jsp").forward(req, resp);
                    session.setAttribute("user", userService.getUserByUserName(userName));
                }
                case "Client" -> {
                    req.getRequestDispatcher("user/client/client.jsp").forward(req, resp);
                    session.setAttribute("user", userService.getUserByUserName(userName));
                }
                case "Staff" -> {
                    req.getRequestDispatcher("user/staff/staff.jsp").forward(req, resp);
                    session.setAttribute("user", userService.getUserByUserName(userName));
                }
            }
        }
    }

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        roleService = new RoleService();
    }
}