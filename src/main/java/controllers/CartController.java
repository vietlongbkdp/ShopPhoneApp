package controllers;

import models.Cart;
import models.EGender;
import models.User;
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

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {
    private ProductService productService;
    private ShoppingService shoppingService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService= new UserService();
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
            case "profile" -> showProfile(req, resp);
            case "editProfile"-> editProfile(req,resp);
            case "deleteCD" -> deleteCD(req, resp);
            case "showCart" -> showCart(req, resp);
            case "cart" -> cart(req, resp);
            case "showDefault" -> cart(req, resp);
            default -> showlist(req, resp);
        }
    }
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

    private void showlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/shopping");
    }

    private void deleteCD(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        shoppingService.deleteCartDetail(Integer.parseInt(req.getParameter("id")));
        String idu =req.getParameter("idu");
        resp.sendRedirect("/cart?action=showCart&id="+idu);
    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =Integer.parseInt(req.getParameter("id"));
        req.setAttribute("user",userService.getUserById(id));
        req.setAttribute("cart", shoppingService.findByUserId(id));
        req.getRequestDispatcher("user/client/cart.jsp").forward(req, resp);
    }

    private void cart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int cartid= shoppingService.findCartIdByUserId(user);
        int id = Integer.parseInt(req.getParameter("id"));
        if (shoppingService.checkProductInCart(cartid, id)) {
            shoppingService.updateCartDetail(shoppingService.findByUserId(user.getId()), id);
        } else {
            shoppingService.createCartDetail(user, req);
        }
        resp.sendRedirect("/shopping");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete" -> delete(req, resp);
            case "buy" -> buy(req, resp);
            case "updateCart" -> updateCart(req, resp);
            case "editProfile"-> updateProfile(req,resp);

        }
    }
    private void updateProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userService.updateProfile(req);
        resp.sendRedirect("/cart?action=profile");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        shoppingService.deleteCartDetails(req.getParameterValues("cartDetailID"));
        String id =req.getParameter("id");
        String url = "/cart?action=showCart&id="+id;
        resp.sendRedirect(url);

    }

    private void updateCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String[] cDetailIDS = req.getParameterValues("cDetailID");
        if (cDetailIDS !=null) {
            shoppingService.updateCartDetails(shoppingService.findByUserId(user.getId()), req);
        }
        resp.sendRedirect("/shopping");
    }

    private void buy(HttpServletRequest req, HttpServletResponse resp) {

    }
}

