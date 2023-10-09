package controllers.products;

import models.Product;
import services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductController", urlPatterns = "/products")
@MultipartConfig(maxFileSize = 16177215)
public class ProductListController extends HttpServlet {

    ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productService.findAll());
        req.getRequestDispatcher("views/products/index.jsp").forward(req,resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ProductService();
    }
}
