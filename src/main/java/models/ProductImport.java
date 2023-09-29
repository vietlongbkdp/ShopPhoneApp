package models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class ProductImport {
    private int id;

    private String code;

    private Date importDate;

    private BigDecimal totalAmount;

    private List<ProductImportDetail> productImportDetails;

    public ProductImport(int id, String code, Date importDate, BigDecimal totalAmount) {
        this.id = id;
        this.code = code;
        this.importDate = importDate;
        this.totalAmount = totalAmount;
    }

    public ProductImport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ProductImportDetail> getProductImportDetails() {
        return productImportDetails;
    }

    public void setProductImportDetails(List<ProductImportDetail> productImportDetails) {
        this.productImportDetails = productImportDetails;
    }
}
