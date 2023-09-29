package models;

import java.math.BigDecimal;

public class ProductImportDetail {
    private int id;

    private BigDecimal amount;

    private int quantity;

    private int quantitySold;

    private Product product;

    private ProductImport productImport;

    public ProductImportDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductImport getProductImport() {
        return productImport;
    }

    public void setProductImport(ProductImport productImport) {
        this.productImport = productImport;
    }
}
