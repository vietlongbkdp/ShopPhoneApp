package services;

import daos.ProductDAO;
import models.Branch;
import models.Product;
import services.dto.Page;

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
    public Page<Product> findAll(int page,boolean isShowRestore,String search){
        return (Page) productDAO.findAll(page,isShowRestore,search);
    }
    public Page<Product>findAllProduct(int page, boolean isShowRestore, String search, String ePriceRange, String branchName){
return  productDAO.findAllProduct(page,isShowRestore,search,ePriceRange,branchName);
    }
    public Page<Product>findAllProductIfNull(int page, boolean isShowRestore, String search, String ePriceRange, String branchName){
        return productDAO.findAllProductIfNULL(page,isShowRestore,search,ePriceRange,branchName);
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

    public boolean checkAvaibleProduct(int productID) {
        var result = true;
        Product product = (Product) productDAO.findById(productID);
        if (product.getQuantity() == 0) {
            result = false;
        }
        return result;
    }public Product findByIdProduct(int id ){
        return  productDAO.findByIdProduct(id);
    }
    public List<Branch> findAllBranch(){
        return  productDAO.findAllBranch();
    }
    public List<Product> findAllProductBestSeller(int limit ){
      return   productDAO.findProductBestSeller(limit);
    }
}

