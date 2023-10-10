package controllers;

import models.EGender;
import models.OrderDetail;
import models.Product;
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
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ClientController", value = "/shopping")
public class ClientController extends HttpServlet {
    private ProductService productService;
    private UserService userService;
    private ShoppingService shoppingService;


    @Override
    public void init() throws ServletException {
        productService = new ProductService();
        userService = new UserService();
        shoppingService = new ShoppingService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
//            case "profile" -> showProfile(req, resp);
            case "editProfile" -> editProfile(req, resp);
            default ->  showProfile(req, resp);

        }
    }

    private void editProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String DetailIDS = req.getParameter("DetailIDS");   //null
        String OrderDTs = req.getParameter("OrderDTs");
//        String quantityB = req.getParameter("quantityB");
//        String idProduct = req.getParameter("idProduct");
        if (DetailIDS != null) {
            req.setAttribute("userEdit", user);
            req.setAttribute("genders", EGender.values());
            req.setAttribute("DetailIDS", DetailIDS);
            req.getRequestDispatcher("user/client/editProfile.jsp").forward(req, resp);
        } else if (OrderDTs != null) {
            req.setAttribute("userEdit", user);
            req.setAttribute("genders", EGender.values());
            req.setAttribute("OrderDTs", OrderDTs);
            req.getRequestDispatcher("user/client/editProfile.jsp").forward(req, resp);
//        } else if (quantityB!=null&&idProduct!=null) {
//            req.setAttribute("userEdit", user);
//            req.setAttribute("genders", EGender.values());
//            req.setAttribute("quantityB", quantityB);
//            req.setAttribute("idProduct", idProduct);
//            req.getRequestDispatcher("user/client/editProfile.jsp").forward(req, resp);
        }else {
            req.setAttribute("userEdit", user);
            req.setAttribute("genders", EGender.values());
            req.getRequestDispatcher("user/client/editProfile.jsp").forward(req, resp);
        }
//        if (DetailIDS == null) {
//            req.setAttribute("userEdit", user);
//            req.setAttribute("genders", EGender.values());
//            req.getRequestDispatcher("user/client/editProfile.jsp").forward(req, resp);
//        } else {
//            req.setAttribute("userEdit", user);
//            req.setAttribute("genders", EGender.values());
//            req.setAttribute("DetailIDS", DetailIDS);
//            req.getRequestDispatcher("user/client/editProfile.jsp").forward(req, resp);
//        }

    }

    private void showProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/user/client/showProfile.jsp").forward(req, resp);
    }

    private void showShopping(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/user/client_undefine/shopping.jsp").forward(req, resp);
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
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "editProfile" -> updateProfile(req, resp);
        }
    }

    private void updateProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String DetailIDS = req.getParameter("DetailIDS");
        String OrderDTs = req.getParameter("OrderDTs");
//        String quantity = req.getParameter("quantityB");
//        String idProduct = req.getParameter("idProduct");
//        if (DetailIDS==null) {
//            userService.updateProfile(req);
//            resp.sendRedirect("/shopping?action=showProfile&message=Update Success");
//        } else {
//            String[] cd = DetailIDS.split(",");
//            userService.updateProfile(req);
//            List<Integer> cartDetailids = Arrays.stream(cd)
//                    .map(Integer::parseInt).toList();
//            req.setAttribute("cartDetails", shoppingService.findListCartDetail(cartDetailids));
//            req.getRequestDispatcher("user/client/createOrder.jsp").forward(req, resp);
//        }
        if (DetailIDS != null) {
            String[] cd = DetailIDS.split(",");
            userService.updateProfile(req);
            List<Integer> cartDetailids = Arrays.stream(cd)
                    .map(Integer::parseInt).toList();
            req.setAttribute("cartDetails", shoppingService.findListCartDetail(cartDetailids));
            req.getRequestDispatcher("user/client/createOrder.jsp").forward(req, resp);
        } else if (OrderDTs != null) {
            int idOrder = Integer.parseInt(req.getParameter("OrderDTs"));
            List<OrderDetail> orderDetails = shoppingService.findAllOD(idOrder);
            req.setAttribute("orderDetails", orderDetails);
            req.getRequestDispatcher("user/client/createOrder.jsp").forward(req, resp);
//
//        } else if (quantity != null && idProduct != null) {
//            int id = Integer.parseInt(req.getParameter("idProduct"));
//            Product product = productService.findByIdProduct(id);
//            int quantity1 = Integer.parseInt(quantity);
//            req.setAttribute("product", product);
//            req.setAttribute("quantity", quantity1);
//            req.getRequestDispatcher("user/client/createOrder.jsp").forward(req, resp);
        } else {
            userService.updateProfile(req);
            resp.sendRedirect("/shopping?action=showProfile&message=Update Success");
        }


    }

}
