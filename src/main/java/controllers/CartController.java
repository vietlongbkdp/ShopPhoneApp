package controllers;

import models.Cart;
import models.User;
import services.ProductService;
import services.ShoppingService;
import services.UserService;
import utils.AuthUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            case "deleteCD" -> deleteCD(req, resp);
            case "showCart" -> showCart(req, resp);
            case "cart" -> cart(req, resp);
            default -> showlist(req, resp);
        }
    }

    private void showlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/shopping");
    }

    private void deleteCD(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        shoppingService.deleteCartDetail(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/cart?action=showCart");
    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =Integer.parseInt(req.getParameter("id"));
        req.setAttribute("user",userService.getUserById(id));
        req.setAttribute("cart", shoppingService.findByUserId(id));
        req.getRequestDispatcher("user/client/cart.jsp").forward(req, resp);
    }

    private void cart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) AuthUtils.getUser();
        int cartid= shoppingService.findCartIdByUserId(user);
        int id = Integer.parseInt(req.getParameter("id"));
        if (shoppingService.checkProductInCart(cartid, id)) {
            shoppingService.updateCartDetail(shoppingService.findByUserId(user.getId()), id);
        } else {
            shoppingService.createCartDetail(AuthUtils.getUser(), req);
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
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        shoppingService.deleteCartDetails(req.getParameterValues("cartDetailID"));
        resp.sendRedirect("/cart?action=showCart");

    }

    private void updateCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] cDetailIDS = req.getParameterValues("cDetailID");
        if (cDetailIDS !=null) {
            shoppingService.updateCartDetails(shoppingService.findByUserId(AuthUtils.getUser().getId()), req);
        }
        resp.sendRedirect("/shopping");
    }

    private void buy(HttpServletRequest req, HttpServletResponse resp) {

    }
}

