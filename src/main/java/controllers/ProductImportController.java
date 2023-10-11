package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
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
        switch (action) {
            case "create":
                showCreate(req, resp);
                break;
            case "edit":
                showEdit(req, resp);
                break;
            case "detail":
                showDetail(req, resp);
                break;
            default:
                showList(req, resp);
        }

    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productImport = productImportService.findById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("productImport", productImport);
        req.getRequestDispatcher("product-import/index_details.jsp").forward(req, resp);
    }


    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showTable(req, "product-import/index.jsp", resp);
    }


    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var products = productService.findAll();
        req.setAttribute("products", products);
        req.setAttribute("productsJSON", new ObjectMapper().writeValueAsString(products));
        req.getRequestDispatcher("product-import/create.jsp").forward(req, resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("productImport", productImportService.findById(Integer.parseInt(req.getParameter("id"))));
        var products = productService.findAll();
        req.setAttribute("products", products);
        req.setAttribute("productsJSON", new ObjectMapper().writeValueAsString(products));
        req.getRequestDispatcher("product-import/edit.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        productImportService.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/product-import?message=Deleted");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                create(req, resp);
                break;
            case "edit":
                edit(req, resp);
                break;

        }
    }





    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        productImportService.edit(req);
        resp.sendRedirect("/product-import?message=Updated Successfully");
    }


    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        productImportService.create(req);
        resp.sendRedirect("/product-import?message=Created Successfully");
    }

    private void showTable(HttpServletRequest req , String href, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("productImports",productImportService.findAllProductImport());
        req.setAttribute("message", req.getParameter("message"));
        req.getRequestDispatcher(href).forward(req, resp);
    }
}
