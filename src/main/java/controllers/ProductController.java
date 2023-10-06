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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

@WebServlet(name = "productController", urlPatterns = "/product")
@MultipartConfig(maxFileSize = 16177215)
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
        req.setAttribute("product",(Product) productService.findById(Integer.parseInt(req.getParameter("id"))));
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
        req.getRequestDispatcher("product/create.jsp").forward(req, resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String productName = req.getParameter("productName");
        String idBranch = req.getParameter("branch");
        Branch branch = new Branch(Integer.parseInt(idBranch));
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        String warrantyPeriod = req.getParameter("warrantyPeriod");
        String ram = req.getParameter("ram");
        String size = req.getParameter("size");
        String color = req.getParameter("color");
        String camera = req.getParameter("camera");
        String operatingSystem = req.getParameter("operatingSystem");
        String pin = req.getParameter("pin");
        String image = null;

        String pathServerImage = getServletContext().getRealPath("/") + "images";
        String pathProjectImage = "F:\\ShopPhoneApp\\src\\main\\webapp\\images";

        String dbImageUrl = null;

        boolean imageUploaded = false;

        for (Part part : req.getParts()) {
            String fileName = extractFileName(part);

            if (!fileName.isEmpty()) {
                fileName = new File(fileName).getName();
                image = "/"+fileName;

                if (part.getContentType().equals("image/jpeg")) {

                    String imagePath = "images" + File.separator + fileName;

//                    part.write(pathProjectImage + File.separator + fileName);
//                    part.write(getServletContext().getRealPath("/") + imagePath);

                    writeImage(pathProjectImage + File.separator + fileName, part);
                    writeImage(getServletContext().getRealPath("/") + imagePath, part);
                    dbImageUrl = imagePath;
                    imageUploaded = true;
                } else {
                    req.setAttribute("errorImage", "File ảnh không hợp lệ!");
                }
            }
        }

        if (!imageUploaded) {
            req.setAttribute("errorImage", "File ảnh không được để trống hoặc không hợp lệ!");
        }
        Product product = new Product(productName, branch, image, price, warrantyPeriod, ram, size, color, camera, operatingSystem, pin);
        productService.create(product);
        resp.sendRedirect("/product?message=Created");
    }

    private Product getProductByRequest(HttpServletRequest req) {
        String productName = req.getParameter("productName");
        String idBranch = req.getParameter("branch");
        Branch branch = new Branch(Integer.parseInt(idBranch));
        String image = req.getParameter("image");
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        String warrantyPeriod = req.getParameter("warrantyPeriod");
        String ram = req.getParameter("ram");
        String size = req.getParameter("size");
        String color = req.getParameter("color");
        String camera = req.getParameter("camera");
        String operatingSystem = req.getParameter("operatingSystem");
        String pin = req.getParameter("pin");
        return new Product(productName, branch, image, price, warrantyPeriod, ram, size, color, camera, operatingSystem, pin);
    }

    public void writeImage(String des, Part part) throws IOException {
        InputStream inputStream = part.getInputStream();

        FileOutputStream fileOutputStream = new FileOutputStream(des);
        int bbyte = -1;
        while ((bbyte = inputStream.read()) != -1) {
            fileOutputStream.write(bbyte);
        }
        fileOutputStream.close();
        inputStream.close();
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
