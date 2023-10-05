package services;

import daos.ProductDAO;
import models.Product;

import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }

    public Object findById(int id) {
        return productDAO.findById(id);
    }

    public Object getProducts(int page, boolean isShowRestore, String search) {
        return productDAO.findAll(page, isShowRestore, search);
    }

    public void update(Product product, int id) {
        product.setId(id);
        productDAO.update(product);
    }

    public void create(Product product) {
        productDAO.create(product);
    }

    public void restore(String[] ids) {
        for (var id : ids) {
            productDAO.restore(Integer.parseInt(id));
        }
    }

    public void delete(int id) {
        productDAO.delete(id);
    }


}

