package services;

import daos.ProductImportDAO;
import models.ProductImport;
import services.dto.Page;
import services.dto.ProductImportListResponse;

public class ProductImportService {
    private ProductImportDAO productImportDAO;

    public ProductImportService() {
        productImportDAO = new ProductImportDAO();
    }

    public Page<ProductImportListResponse> findAll(){
        return (Page<ProductImportListResponse>) productImportDAO.findAll();
    }

    public void delete(int id) {
        productImportDAO.deleteImportDetail(id);
    }
    public ProductImport findById(int id){
        return productImportDAO.findById(id);
    }
}
