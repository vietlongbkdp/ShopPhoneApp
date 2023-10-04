package controllers;

import daos.RoleDao;
import daos.UserDao;
import models.EGender;
import models.User;
import services.RoleService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            case "userManager":
                showListUser(req, resp);
                break;
            case "product":
                showListProduct(req, resp);
                break;
            case "order":
                showListOrder(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "edit":
                showEdit(req, resp);
                break;
            default:
                showTotal(req, resp);
        }
    }

    private void showProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userLogin = (User) session.getAttribute("user");
        req.setAttribute("user", userLogin);
        req.getRequestDispatcher("/user/client/editProfile.jsp").forward(req, resp);
    }

    private void showListOrder(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void showListProduct(HttpServletRequest req, HttpServletResponse resp) {
        
    }

    private void showTotal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.getAllUser());
        req.getRequestDispatcher("user/admin/managerTotal.jsp").forward(req,resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userEdit", userService.getUserById(Integer.parseInt(req.getParameter("id"))));
        req.setAttribute("roles", roleService.getAllRole());
        req.setAttribute("genders", EGender.values());
        req.getRequestDispatcher("user/admin/edit.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.deleteUserById(id);
        req.setAttribute("users", userService.getAllUser());
        req.getRequestDispatcher("user/admin/managerUser.jsp").forward(req,resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", roleService.getAllRole());
        req.setAttribute("genders", EGender.values());
        req.getRequestDispatcher("user/admin/create.jsp").forward(req, resp);
    }

    private void showListUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.getAllUser());
        req.getRequestDispatcher("user/admin/managerUser.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                create(req, resp);
                break;
            case "edit":
                edit(req, resp);
                break;
            default:
                showListUser(req, resp);
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String password = req.getParameter("password");
        String re_password = req.getParameter("re_password");
        if(password.equals(re_password)){
            userService.edit(req);
            resp.sendRedirect("/admin?action=userManager?message=Edit Success");
        }else resp.sendRedirect("/admin?action=userManager?message=Edit Fail");
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String password = req.getParameter("password");
        String re_password = req.getParameter("re_password");
        if(password.equals(re_password)){
            userService.createUser(req);
            resp.sendRedirect("/admin?action=userManager?message=Create Success");
        }else resp.sendRedirect("/admin?action=userManager?message=Create Fail");
    }

    @Override
    public void init() throws ServletException {
        roleService = new RoleService();
        userService = new UserService();
    }
}
