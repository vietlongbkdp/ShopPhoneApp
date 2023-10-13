package controllers;

import daos.OrderDao;
import services.BranchService;
import services.ProductService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "TotalAdminController", value = "/total")
public class TotalAdminController extends HttpServlet {
    private UserService userService;
    private BranchService branchService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "product":
                showListProduct(req, resp);
                break;
            case "branch":
                showBranch(req, resp);
                break;
            case "order":
                showOrder(req, resp);
                break;
            default:
                showTotal(req, resp);
        }
    }

    private void showTotal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user/client/totalAdmin.jsp").forward(req, resp);
    }

    private void showOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("/order");
    }

    private void showBranch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("num_branch",branchService.getBranchs().size() );
        req.getRequestDispatcher("/user/staff/branchTotal.jsp").forward(req, resp);
    }

    private void showListProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user/staff/productTotal.jsp").forward(req, resp);
    }

    private void showListUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("num_Admin", userService.getQuantityAdmin());
        req.setAttribute("num_Staff", userService.getQuantityStaff());
        req.setAttribute("num_Client", userService.getQuantityClient());
        int num_User_total = userService.getQuantityAdmin() + userService.getQuantityStaff() + userService.getQuantityClient();
        req.setAttribute("num_User_total", num_User_total);
        req.getRequestDispatcher("/user/admin/userTotal.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        branchService = new BranchService();
    }
}
