package controllers;

import models.EStatus;
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

@WebServlet(name = "OrderClientControler", value = "/order-client")
public class OrderClientController extends HttpServlet {
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
            case "orderConfirming" -> showOrderConfirming(req, resp);
            case "orderConfirmed" -> showOrderConfirmed(req, resp);
            case "orderCanceled" -> showOrderCanceled(req, resp);
            default -> showOrder(req, resp);
        }
    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("orders", shoppingService.findAllByUser("CONFIRMING", user.getId()));
        req.setAttribute("orderCM", shoppingService.findAllByUser("CONFIRMING", user.getId()));
        req.setAttribute("orderCD", shoppingService.findAllByUser("CONFIRMED", user.getId()));
        req.setAttribute("orderC", shoppingService.findAllByUser("CANCELED", user.getId()));
        int idOrder = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("orderDetails", shoppingService.findAllOD(idOrder));
        req.getRequestDispatcher("/user/client/orderDetail.jsp").forward(req, resp);
    }

    private void showOrderConfirming(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("orders", shoppingService.findAllByUser("CONFIRMING", user.getId()));
        req.setAttribute("orderCM", shoppingService.findAllByUser("CONFIRMING", user.getId()));
        req.setAttribute("orderCD", shoppingService.findAllByUser("CONFIRMED", user.getId()));
        req.setAttribute("orderC", shoppingService.findAllByUser("CANCELED", user.getId()));
        req.getRequestDispatcher("/user/client/orderConfirming.jsp").forward(req, resp);
    }

    private void showOrderConfirmed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("orders", shoppingService.findAllByUser("CONFIRMED", user.getId()));
        req.setAttribute("orderCM", shoppingService.findAllByUser("CONFIRMING", user.getId()));
        req.setAttribute("orderCD", shoppingService.findAllByUser("CONFIRMED", user.getId()));
        req.setAttribute("orderC", shoppingService.findAllByUser("CANCELED", user.getId()));
        req.getRequestDispatcher("/user/client/orderConfirmed.jsp").forward(req, resp);

    }

    private void showOrderCanceled(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("orders", shoppingService.findAllByUser("CANCELED", user.getId()));
        req.setAttribute("orderCM", shoppingService.findAllByUser("CONFIRMING", user.getId()));
        req.setAttribute("orderCD", shoppingService.findAllByUser("CONFIRMED", user.getId()));
        req.setAttribute("orderC", shoppingService.findAllByUser("CANCELED", user.getId()));
        req.getRequestDispatcher("/user/client/orderCanceled.jsp").forward(req, resp);

    }

    private void showOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> orderList = shoppingService.findAllByUser("CONFIRMING", user.getId());
        req.setAttribute("orderCM", shoppingService.findAllByUser("CONFIRMING", user.getId()));
        req.setAttribute("orderCD", shoppingService.findAllByUser("CONFIRMED", user.getId()));
        req.setAttribute("orderC", shoppingService.findAllByUser("CANCELED", user.getId()));
        req.getRequestDispatcher("/user/client/orderClient.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
