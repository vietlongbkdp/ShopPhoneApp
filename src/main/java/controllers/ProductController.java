package controllers;


import models.Branch;
import models.Product;
import services.BranchService;
import services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)  // 50MB

@WebServlet(name = "productController", urlPatterns = "/product")
public class ProductController extends HttpServlet {
    private ProductService productService;
    private BranchService branchService;

    public void init() throws ServletException {
        productService = new ProductService();
        branchService = new BranchService();

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create" -> showCreate(req, resp);
            case "edit" -> showEdit(req, resp);
            case "restore" -> showRestore(req, resp);
            case "delete" -> delete(req, resp);
            default -> showList(req, resp);
        }
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showTable(req, false, resp);

    }

    private void showRestore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showTable(req, true, resp);
    }

    private void showTable(HttpServletRequest req, boolean isShowRestore, HttpServletResponse resp) throws ServletException, IOException {
        String pageString = req.getParameter("page");
        if (pageString == null) {
            pageString = "1";
        }
        req.setAttribute("page", productService.getProducts(Integer.parseInt(pageString), isShowRestore, req.getParameter("search")));
        req.setAttribute("message", req.getParameter("message"));
        req.setAttribute("isShowRestore", isShowRestore);
        req.setAttribute("search", req.getParameter("search"));
        req.getRequestDispatcher("product/index.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        productService.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/product?message=Deleted");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create" -> create(req, resp);
            case "edit" -> edit(req, resp);
            case "restore" -> restore(req, resp);
        }
    }

    private void restore(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] check = req.getParameterValues("restore");
        if (check != null) {
            productService.restore(check);
        }
        resp.sendRedirect("/product?message=Restored");
    }


    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("product", productService.findById(Integer.parseInt(req.getParameter("id"))));
        req.setAttribute("branchs", branchService.getBranchs());
        req.getRequestDispatcher("product/create.jsp").forward(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        productService.update(getProductByRequest(req), Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/product?message=Updated");
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("product", new Product());
        req.setAttribute("branchs", branchService.getBranchs());
        req.getRequestDispatcher("product/create.jsp").forward(req,resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String productName = req.getParameter("productName");
        String idBranch = req.getParameter("branch");
        Branch branch = new Branch(Integer.parseInt(idBranch));
        String image = req.getParameter("image");
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        String quantity = req.getParameter("quantity");
        String warrantyPeriod = req.getParameter("warrantyPeriod");
        String ram = req.getParameter("ram");
        String size = req.getParameter("size");
        String color = req.getParameter("color");
        String camera = req.getParameter("camera");
        String operatingSystem = req.getParameter("operatingSystem");
        String pin = req.getParameter("pin");
        Product product = new Product(productName, branch, image, price, quantity, warrantyPeriod, ram, size, color, camera, operatingSystem, pin);
        String pathServerImage = getServletContext().getRealPath("/") + "images";
        String pathProjectImage  = "F:\\ShopPhoneApp\\src\\main\\webapp\\images";

        String dbImageUrl = null;

        for (Part part : req.getParts()) {
            String fileName = extractFileName(part);

            if(!fileName.isEmpty()){
                fileName = new File(fileName).getName();

                if(part.getContentType().equals("image/jpeg")){
                    part.write(pathProjectImage + File.separator + fileName);
                    dbImageUrl = File.separator + fileName;
                    dbImageUrl = dbImageUrl.replace("\\","/");
                    part.write(pathServerImage + File.separator + fileName);
                }
            }
        }
        if (dbImageUrl == null) {
            req.setAttribute("errorImage", "File ảnh không được để trống!");
        } else {
            product.setImage(dbImageUrl);
        }
        productService.create(product);
        resp.sendRedirect("/product?message=Created");
    }

    private Product getProductByRequest(HttpServletRequest req) {
        String productName = req.getParameter("productName");
        String idBranch = req.getParameter("branch");
        Branch branch = new Branch(Integer.parseInt(idBranch));
        String image = req.getParameter("image");
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        String quantity = req.getParameter("quantity");
        String warrantyPeriod = req.getParameter("warrantyPeriod");
        String ram = req.getParameter("ram");
        String size = req.getParameter("size");
        String color = req.getParameter("color");
        String camera = req.getParameter("camera");
        String operatingSystem = req.getParameter("operatingSystem");
        String pin = req.getParameter("pin");
        return new Product(productName, branch, image, price, quantity, warrantyPeriod, ram, size, color, camera, operatingSystem, pin);
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}
