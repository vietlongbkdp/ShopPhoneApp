package controllers;

import daos.RoleDao;
import daos.UserDao;
import models.EGender;
import services.RoleService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserController", value = "/user")
public class  UserController extends HttpServlet {
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
            case "userManager":
                showListUser(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.deleteUserById(id);
        req.setAttribute("users", userService.getAllUser());
        req.getRequestDispatcher("user/admin/managerUser.jsp").forward(req,resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", roleService.getAllRole());
        req.setAttribute("gender", EGender.values());
        req.getRequestDispatcher("user/admin/create.jsp").forward(req, resp);
    }

    private void showListUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.getAllUser());
        req.getRequestDispatcher("user/admin/managerUser.jsp").forward(req,resp);
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
