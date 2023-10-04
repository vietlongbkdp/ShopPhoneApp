package controllers;

import models.EGender;
import models.User;
import services.ProductService;
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

@WebServlet(name = "ClientController", value = "/shopping")
public class ClientController extends HttpServlet {
    private ProductService productService;
    private UserService userService;
    private RoleService roleService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
        userService = new UserService();
        roleService = new RoleService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add"-> add(req,resp);
            case "profile" -> showProfile(req, resp);
            case "editProfile"-> editProfile(req,resp);
            default -> showlist(req, resp);
        }
    }


    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void showlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showTable(req, false, "user/client/client.jsp", resp);
    }

    private void showTable(HttpServletRequest req, boolean isShowRestore, String href, HttpServletResponse resp) throws ServletException, IOException {
        String pageString = req.getParameter("page");
        if (pageString == null) {
            pageString = "1";
        }
        User user= AuthUtils.getUser();
        req.setAttribute("user",user);
        req.setAttribute("page", productService.getProducts(Integer.parseInt(pageString), isShowRestore, req.getParameter("search")));
        req.setAttribute("message", req.getParameter("message"));
        req.setAttribute("isShowRestore", isShowRestore);
        req.setAttribute("search", req.getParameter("search"));
        req.getRequestDispatcher(href).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "editProfile"-> updateProfile(req,resp);
        }
    }

    private void updateProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userService.updateProfile(req);
        resp.sendRedirect("/shopping?action=profile");
    }

//    private void updateProfile(HttpServletRequest req, HttpServletResponse resp) {
//        userService.editProfile(req);
//        resp.sendRedirect();
//    }

    private void showProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user", userService.getUserById(user.getId()));
        req.getRequestDispatcher("/user/client/profileUser.jsp").forward(req, resp);
    }

    private void editProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userEdit", userService.getUserById(Integer.parseInt(req.getParameter("id"))));
        req.setAttribute("genders", EGender.values());
        req.getRequestDispatcher("user/client/editProfile.jsp").forward(req, resp);
    }


}
