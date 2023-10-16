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
            case "profile" -> showProfile(req, resp);
            case "editProfile" -> editProfile(req, resp);
            default ->  showlist(req, resp);

        }
    }

    private void editProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String DetailIDS = req.getParameter("DetailIDS");
        String OrderDTs = req.getParameter("OrderDTs");
        String quantityB = req.getParameter("quantityB");
        String idProduct = req.getParameter("idProduct");
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
        } else if (quantityB!=null&&idProduct!=null) {
            req.setAttribute("userEdit", user);
            req.setAttribute("genders", EGender.values());
            req.setAttribute("quantityB", quantityB);
            req.setAttribute("idProduct", idProduct);
            req.getRequestDispatcher("user/client/editProfile.jsp").forward(req, resp);
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
        resp.sendRedirect("/main");
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
        String quantity = req.getParameter("quantityB");
        String idProduct = req.getParameter("idProduct");
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
        if (!DetailIDS.isEmpty()) {
            String[] cd = DetailIDS.split(",");
            userService.updateProfile(req);
            List<Integer> cartDetailids = Arrays.stream(cd)
                    .map(Integer::parseInt).toList();
            req.setAttribute("cartDetails", shoppingService.findListCartDetail(cartDetailids));
            req.getRequestDispatcher("user/client/crtOrder.jsp").forward(req, resp);
        } else if (!OrderDTs.isEmpty()) {
            userService.updateProfile(req);
            int idOrder = Integer.parseInt(req.getParameter("OrderDTs"));
            List<OrderDetail> orderDetails = shoppingService.findAllOD(idOrder);
            req.setAttribute("orderDetails", orderDetails);
            req.getRequestDispatcher("user/client/crtOrder.jsp").forward(req, resp);
        } else if (!quantity.isEmpty()&& !idProduct.isEmpty()) {
            userService.updateProfile(req);
            int id = Integer.parseInt(req.getParameter("idProduct"));
            Product product = productService.findByIdProduct(id);
            int quantity1 = Integer.parseInt(quantity);
            req.setAttribute("product", product);
            req.setAttribute("quantityB", quantity1);
            req.getRequestDispatcher("user/client/crtOrder.jsp").forward(req, resp);
        } else {
            userService.updateProfile(req);
            resp.sendRedirect("/shopping?action=profile&message=Update Success");
        }


    }

}
