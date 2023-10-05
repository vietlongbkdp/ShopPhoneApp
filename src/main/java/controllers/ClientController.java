package controllers;

import models.EGender;
import models.User;
import services.ProductService;
import services.ShoppingService;
import services.RoleService;
import services.UserService;



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


    @Override
    public void init() throws ServletException {
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "shopping" -> showShopping(req, resp);
            default -> showlist(req, resp);
        }
    }

    private void showShopping(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user/cliented/shopping.jsp").forward(req, resp);
    }


    private void showlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showTable(req, false, "user/client/client.jsp", resp);
    }

    private void showTable(HttpServletRequest req, boolean isShowRestore, String href, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String pageString = req.getParameter("page");
        if (pageString == null) {
            pageString = "1";
        }
        if (user == null) {
            req.setAttribute("page", productService.getProducts(Integer.parseInt(pageString), isShowRestore, req.getParameter("search")));
            req.setAttribute("message", req.getParameter("message"));
            req.setAttribute("isShowRestore", isShowRestore);
            req.setAttribute("search", req.getParameter("search"));
            req.getRequestDispatcher(href).forward(req, resp);
        } else if (user != null) {
            req.setAttribute("user", user);
            req.setAttribute("page", productService.getProducts(Integer.parseInt(pageString), isShowRestore, req.getParameter("search")));
            req.setAttribute("message", req.getParameter("message"));
            req.setAttribute("isShowRestore", isShowRestore);
            req.setAttribute("search", req.getParameter("search"));
            req.getRequestDispatcher(href).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
