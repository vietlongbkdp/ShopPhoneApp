package controllers;

import models.Order;
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
import java.util.List;

@WebServlet(name = "OrderController", value = "/order")
public class OrderController extends HttpServlet {
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
            case "detail" -> showDetail(req, resp);
            case "confirm" -> confirmOrder(req, resp);
            case "cancel" -> cancelOrder(req, resp);
            case "showConfirming" -> showConfirming(req, resp);
            case "showConfirmed" -> showConfirmed(req, resp);
            case "showCanceled" -> showCanceled(req, resp);
            default -> showOrder(req, resp);
        }
    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orderCM", shoppingService.findAllByStatus("CONFIRMING"));
        req.setAttribute("orderCD", shoppingService.findAllByStatus("CONFIRMED"));
        req.setAttribute("orderC", shoppingService.findAllByStatus("CANCELED"));
        int idOrder = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("orderDetails",shoppingService.findAllOD(idOrder));
        req.getRequestDispatcher("/user/admin/orderDetail.jsp").forward(req, resp);
    }

    private void showCanceled(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("orderCM", shoppingService.findAllByStatus("CONFIRMING"));
        req.setAttribute("orderCD", shoppingService.findAllByStatus("CONFIRMED"));
        req.setAttribute("orderC", shoppingService.findAllByStatus("CANCELED"));
        req.getRequestDispatcher("/user/admin/orderCanceled.jsp").forward(req, resp);
    }

    private void showConfirmed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("orderCM", shoppingService.findAllByStatus("CONFIRMING"));
        req.setAttribute("orderCD", shoppingService.findAllByStatus("CONFIRMED"));
        req.setAttribute("orderC", shoppingService.findAllByStatus("CANCELED"));
        req.getRequestDispatcher("/user/admin/orderConfirmed.jsp").forward(req, resp);

    }

    private void showConfirming(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("orderCM", shoppingService.findAllByStatus("CONFIRMING"));
        req.setAttribute("orderCD", shoppingService.findAllByStatus("CONFIRMED"));
        req.setAttribute("orderC", shoppingService.findAllByStatus("CANCELED"));
        req.getRequestDispatcher("/user/admin/orderConfirming.jsp").forward(req, resp);

    }

    private void confirmOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        shoppingService.updateOrderStatus("CONFIRMED", id);
        resp.sendRedirect("order?action=showConfirming");
    }

    private void cancelOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        shoppingService.updateOrderStatus("CANCELED", id);
        resp.sendRedirect("order?action=showConfirming");

    }

    private void showOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("orderCM", shoppingService.findAllByStatus("CONFIRMING"));
        req.setAttribute("orderCD", shoppingService.findAllByStatus("CONFIRMED"));
        req.setAttribute("orderC", shoppingService.findAllByStatus("CANCELED"));
        req.getRequestDispatcher("/user/admin/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
