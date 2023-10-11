package services;

import daos.ProductDAO;
import models.Branch;
import models.Product;

import services.dto.AllProductDto;

import services.dto.Page;


import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }

    public Product findById(int id) {
        return productDAO.findById(id);
    }

    public Object getProducts(int page, boolean isShowRestore, String search) {
        return productDAO.findAll(page, isShowRestore, search);
    }

    public Page<Product> findAll(int page, boolean isShowRestore, String search) {
        return (Page) productDAO.findAll(page, isShowRestore, search);
    }

    public Page<Product> findAllProduct(int page, boolean isShowRestore, String search, String ePriceRange, String branchName) {
        return productDAO.findAllProduct(page, isShowRestore, search, ePriceRange, branchName);
    }

    public Page<Product> findAllProductIfNull(int page, boolean isShowRestore, String search, String ePriceRange, String branchName) {
        return productDAO.findAllProductIfNULL(page, isShowRestore, search, ePriceRange, branchName);
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

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public List<AllProductDto> findAllProductDto(boolean deleted) {
        return productDAO.findAllProductDto(deleted);
    }

    public boolean checkAvaibleProduct(int productID, int quantity) {
        var result = true;
        Product product = (Product) productDAO.findById(productID);
        if (product.getQuantity() == 0 || (product.getQuantity() < quantity)) {
            result = false;
        }
        return result;
    }

    public Product findByIdProduct(int id) {
        return productDAO.findByIdProduct(id);
    }

    public List<Branch> findAllBranch() {
        return productDAO.findAllBranch();
    }

    public List<Product> findAllProductBestSeller(int limit) {
        return productDAO.findProductBestSeller(limit);
    }

    public Page<Product> findProduct(int page, String search, String ePriceRange, String branchName) {
        return productDAO.findAll(page, search, ePriceRange, branchName);
    }
}

