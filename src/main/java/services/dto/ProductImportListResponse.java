package services.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class ProductImportListResponse {
    private int id;

    private String code;

    private Date importDate;

    private String products;

    private BigDecimal totalAmount;

    public ProductImportListResponse() {
    }

    public ProductImportListResponse(int id, String code, Date importDate, String products, BigDecimal totalAmount) {
        this.id = id;
        this.code = code;
        this.importDate = importDate;
        this.products = products;
        this.totalAmount = totalAmount;
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

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
