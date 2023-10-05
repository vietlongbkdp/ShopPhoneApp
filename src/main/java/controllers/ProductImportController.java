package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import services.ProductImportService;
import services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "productImportController", urlPatterns = "/product-import")
public class ProductImportController extends HttpServlet {
    private ProductService productService;

    private ProductImportService productImportService;

    public void init() throws ServletException {
        productService = new ProductService();
        productImportService = new ProductImportService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        if (action.equals("delete")) {
            delete(req, resp);
            return;
        }
        if (action.equals("edit")) {
            showEdit(req, resp);
            return;
        }
////        if (action.equals("create")) {
//////            showCreate(req, resp);
////            return;
//        }
        showList(req, resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) {
    }

//    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var products = productService.getProducts();
//        req.setAttribute("products", products);
//        req.setAttribute("productsJSON", new ObjectMapper().writeValueAsString(products));
//        req.getRequestDispatcher("product-import/create.jsp").forward(req, resp);
//    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if("edit".equals(action)){
            edit(req, resp);
            return;
        }
        if("create".equals(action)){
            create(req, resp);
            return;
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) {
    }
}
