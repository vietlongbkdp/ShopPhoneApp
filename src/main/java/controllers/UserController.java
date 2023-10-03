package controllers;

import daos.RoleDao;
import daos.UserDao;
import services.RoleService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserController", value = "/admin")
public class UserController extends HttpServlet {
    private UserService userService;
    private RoleService roleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreate(req, resp);
                break;
            default:
                showListUser(req, resp);
        }
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void showListUser(HttpServletRequest req, HttpServletResponse resp) {
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        roleService = new RoleService();
        userService = new UserService();
    }
}
