package controllers;

import models.User;
import services.ProductService;
import services.ShoppingService;
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
    private ShoppingService shoppingService;


    @Override
    public void init() throws ServletException {
        productService = new ProductService();
        shoppingService = new ShoppingService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default -> showlist(req, resp);
        }
    }
    private void showlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showTable(req, false, "user/client/client.jsp", resp);
    }

    private void showTable(HttpServletRequest req, boolean isShowRestore, String href, HttpServletResponse resp) throws ServletException, IOException {
        String pageString = req.getParameter("page");
        if (pageString == null) {
            pageString = "1";
        }
        if (AuthUtils.getUser() == null) {
            req.setAttribute("page", productService.getProducts(Integer.parseInt(pageString), isShowRestore, req.getParameter("search")));
            req.setAttribute("message", req.getParameter("message"));
            req.setAttribute("isShowRestore", isShowRestore);
            req.setAttribute("search", req.getParameter("search"));
            req.getRequestDispatcher(href).forward(req, resp);
        } else if (AuthUtils.getUser() != null) {
            User user = AuthUtils.getUser();
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
