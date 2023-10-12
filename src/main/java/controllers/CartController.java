package controllers;

import models.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {
    private ProductService productService;
    private ShoppingService shoppingService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
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
            case "showDefault" -> showDefault(req, resp);
            case "reBuy" -> reBuy(req, resp);
            default -> showlist(req, resp);
        }
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
            case "buyNow" -> buyNow(req, resp);
            case "addCart" -> addCart(req, resp);
            case "updateCart" -> updateCart(req, resp);
            case "payment" -> payment(req, resp);
        }
    }

    private void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int idProduct = Integer.parseInt(req.getParameter("idProduct"));
        int quantity = Integer.parseInt(req.getParameter("quantityB"));
        Cart cart = shoppingService.findByUserId(user.getId());
        Product product = productService.findByIdProduct(idProduct);
        if (product.getQuantity() == 0) {
            resp.sendRedirect("/main?action=showDetailProduc&id=" + idProduct + "&message=" + product.getProductName() + " is out of stock ");
            return;
        }
        if (shoppingService.checkProductInCart(cart.getId(), idProduct)) {
            shoppingService.updateCartDetail(shoppingService.findByUserId(user.getId()), idProduct, quantity);
        } else {
            shoppingService.createCartDetail(user, idProduct, quantity);
        }
        resp.sendRedirect("/main?action=showDetailProduct&id=" + idProduct);

    }

    private void buyNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idProduct = Integer.parseInt(req.getParameter("idProduct"));
        int quantity = Integer.parseInt(req.getParameter("quantityB"));
        Product product = productService.findByIdProduct(idProduct);
        if (product.getQuantity() == 0) {
            resp.sendRedirect("/main?action=showDetailProduc&id=" + idProduct + "&message=" + product.getProductName() + " is out of stock ");
            return;
        }
        req.setAttribute("product", product);
        req.setAttribute("quantityB", quantity);
        req.getRequestDispatcher("user/client/crtOrder.jsp").forward(req, resp);
    }

    private void reBuy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idOrder = Integer.parseInt(req.getParameter("id"));
        List<OrderDetail> orderDetails = shoppingService.findAllOD(idOrder);
        String urlLink = req.getParameter("url");
        for (var orderDetail : orderDetails) {
            if (!productService.checkAvaibleProduct(orderDetail.getProduct().getId(), orderDetail.getQuantity())) {
                Product product = (Product) productService.findById(orderDetail.getProduct().getId());
                String productName = product.getProductName();
                String url = "";
                if (urlLink.equals("Confirming")) {
                    url = "/order-client?action=orderConfirming&message=" + productName + " is not enough";
                } else if (urlLink.equals("Confirmed")) {
                    url = "/order-client?action=orderConfirmed&message=" + productName + " is not enough";
                } else if (urlLink.equals("Canceled")) {
                    url = "/order-client?action=orderCanceled&message=" + productName + "is not enough";
                }
                resp.sendRedirect(url);
                return;
            }
        }
        req.setAttribute("OrderDTs", idOrder);
        req.setAttribute("orderDetails", orderDetails);
        req.getRequestDispatcher("user/client/crtOrder.jsp").forward(req, resp);
    }

    private void showDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user/client/showClient.jsp").forward(req, resp);
    }

    private void showlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/main");
    }

    private void deleteCD(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        shoppingService.deleteCartDetail(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/cart?action=showCart");
    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user", user);
        req.setAttribute("cart", shoppingService.findByUserId(user.getId()));
        req.setAttribute("message", req.getAttribute("message"));
        req.getRequestDispatcher("user/client/showCart.jsp").forward(req, resp);
    }

    private void cart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int cartid = shoppingService.findCartIdByUserId(user);
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findByIdProduct(id);
        if (product.getQuantity() == 0) {
            resp.sendRedirect("/main?message=" + product.getProductName() + "is out of stock ");
            return;
        }
        if (shoppingService.checkProductInCart(cartid, id)) {
            shoppingService.updateCartDetail(shoppingService.findByUserId(user.getId()), id, 1);
        } else {
            shoppingService.createCartDetail(user, id, 1);
        }
        // chỉnh lại
        resp.sendRedirect("/main");

    }


    private void payment(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String DetailIDS = req.getParameter("DetailIDS");
        String OrderDTs = req.getParameter("OrderDTs");
        String quantityB = req.getParameter("quantityB");
        String idProduct = req.getParameter("productIds");
        if (!userService.checkProfileUser(user.getId())) {
            if (DetailIDS != null) {
                resp.sendRedirect("/shopping?action=editProfile&DetailIDS=" + DetailIDS);
                return;
            } else if (OrderDTs != null) {
                resp.sendRedirect("/shopping?action=editProfile&OrderDTs=" + OrderDTs);
                return;
            } else if (quantityB != null && idProduct != null) {
                resp.sendRedirect("/shopping?action=editProfile&quantityB=" + quantityB + "&idProduct=" + idProduct);
                return;
            }
        }
        String[] listCartDetais= DetailIDS.split(",");
        List<Integer> listCartDetailID =Arrays.stream(listCartDetais)
                .map(Integer::parseInt).toList();
        shoppingService.deleteListCartDetail(listCartDetailID);
        List<Integer> quantities = Arrays.stream(req.getParameterValues("quantities"))
                .map(Integer::parseInt).toList();
        List<Integer> productIds = Arrays.stream(req.getParameterValues("productIds"))
                .map(Integer::parseInt).toList();
        int id = shoppingService.createOrder(user.getId());
        shoppingService.createOrderDetails(quantities, productIds, id);
        resp.sendRedirect("/order-client?action=orderConfirming");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        shoppingService.deleteCartDetails(req.getParameterValues("cartDetailID"));
        resp.sendRedirect("/cart?action=showCart");

    }

    private void updateCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String[] cDetailIDS = req.getParameterValues("cDetailID");
        if (cDetailIDS != null) {
            shoppingService.updateCartDetails(shoppingService.findByUserId(user.getId()), req);
        }
        resp.sendRedirect("/main");
    }

    private void buy(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String[] cDetailIDS = req.getParameterValues("cDetailID");
        Cart cart = shoppingService.findByUserId(user.getId());
        if (cDetailIDS == null) {
            resp.sendRedirect("/main?message=Please choose product");
        } else if (cDetailIDS != null) {
            shoppingService.updateCartDetails(cart, req);
            var listCartDetailChoosen = shoppingService.cartDetails(shoppingService.findByUserId(user.getId()).getId(), 1);
            for (var cartDetail : listCartDetailChoosen) {
                if (!productService.checkAvaibleProduct(cartDetail.getProduct().getId(), cartDetail.getQuantity())) {
                    Product product = (Product) productService.findById(cartDetail.getProduct().getId());
                    String productName = product.getProductName();
                    String url = "/cart?action=showCart&message=" + productName + " is not enough";
                    resp.sendRedirect(url);
                    return;
                }
            }
            String DetailIDS = "";
            for (var i = 0; i < listCartDetailChoosen.size(); i++) {
                DetailIDS += listCartDetailChoosen.get(i).getId();
                if (i != listCartDetailChoosen.size() - 1) {
                    DetailIDS += ",";
                }
            }
            req.setAttribute("DetailIDS", DetailIDS);
            req.setAttribute("cartDetails", shoppingService.cartDetails(cart.getId(), 1));
            req.getRequestDispatcher("user/client/crtOrder.jsp").forward(req, resp);
        }
    }
}


