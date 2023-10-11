package controllers;

import daos.ProductDAO;
import models.Branch;
import models.EPriceRange;
import models.Product;
import models.User;
import services.BranchService;
import services.ProductService;
import services.ShoppingService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MainController", value = "/main")

public class MainController extends HttpServlet {
    private ProductService productService;
    private ProductDAO productDAO;
    private UserService userService;
    private ShoppingService shoppingService;
    private BranchService branchService;
    @Override
    public void init() throws ServletException {
        productService = new ProductService();
        userService = new UserService();
        shoppingService = new ShoppingService();
        productDAO = new ProductDAO();
        branchService= new BranchService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "showDetailProduct" -> showDetailProduct(req, resp);
            case "detail"->showDetail(req,resp);
            default -> showShopping(req, resp);
        }
    }

    private void showDetailProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productDAO.findById(id);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user", user);
        req.setAttribute("product", product);
        req.getRequestDispatcher("/user/client_undefine/productDetailShow.jsp").forward(req, resp);
    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findByIdProduct(id);
        req.setAttribute("product", product);
        req.getRequestDispatcher("/user/client/shoppingDetail.jsp").forward(req, resp);
    }

    private void showlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/user/client_undefine/shopping.jsp").forward(req, resp);
    }

    private void showShopping(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        show(req,  "user/client_undefine/shopping.jsp", resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }




//    private void showTable(HttpServletRequest req, boolean isShowRestore, String href, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        String pageString = req.getParameter("page");
//        if (pageString == null) {
//            pageString = "1";
//        }
//        if (user == null) {
//            req.setAttribute("page", productService.getProducts(Integer.parseInt(pageString), isShowRestore, req.getParameter("search")));
//            req.setAttribute("message", req.getParameter("message"));
//            req.setAttribute("isShowRestore", isShowRestore);
//            req.setAttribute("search", req.getParameter("search"));
//            req.getRequestDispatcher(href).forward(req, resp);
//        } else if (user != null) {
//            req.setAttribute("user", user);
//            req.setAttribute("page", productService.getProducts(Integer.parseInt(pageString), isShowRestore, req.getParameter("search")));
//            req.setAttribute("message", req.getParameter("message"));
//            req.setAttribute("isShowRestore", isShowRestore);
//            req.setAttribute("search", req.getParameter("search"));
//            req.getRequestDispatcher(href).forward(req, resp);
//        }
//    }

    private void show(HttpServletRequest req, String href, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String pageString = req.getParameter("page");
        if (pageString == null) {
            pageString = "1";
        }
        String ePriceRange = req.getParameter("ePriceRange");
        String branch = req.getParameter("branch");
        List<Product> productList = productService.findAllProductBestSeller(3);
        if (user == null) {
            req.setAttribute("page", productService.findProduct(Integer.parseInt(pageString), req.getParameter("search"), req.getParameter("ePriceRange"), req.getParameter("branch")));
            req.setAttribute("branch", branchService.getBranchByName(branch));
            req.setAttribute("productBSs", productService.findAllProductBestSeller(3));
            req.setAttribute("ePriceRange",EPriceRange.valueOf(ePriceRange));
            req.setAttribute("branchs", productService.findAllBranch());
            req.setAttribute("PriceRange", EPriceRange.values());
            req.setAttribute("message", req.getParameter("message"));
            req.setAttribute("search", req.getParameter("search"));
            req.getRequestDispatcher(href).forward(req, resp);

        } else if (user != null) {
            req.setAttribute("user", user);
            req.setAttribute("page", productService.findProduct(Integer.parseInt(pageString), req.getParameter("search"), req.getParameter("ePriceRange"), req.getParameter("branch")));
            req.setAttribute("branch", branchService.getBranchByName(branch));
            req.setAttribute("ePriceRange", EPriceRange.valueOf(ePriceRange));
            req.setAttribute("productBSs", productService.findAllProductBestSeller(3));
            req.setAttribute("message", req.getParameter("message"));
            req.setAttribute("search", req.getParameter("search"));
            req.setAttribute("branchs", productService.findAllBranch());
            req.setAttribute("PriceRange", EPriceRange.values());
            req.getRequestDispatcher(href).forward(req, resp);

        }
    }
}
