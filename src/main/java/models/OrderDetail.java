package models;

import java.math.BigDecimal;

public class OrderDetail {
    private int id ;
    private int quantity;
    private BigDecimal totalAmount;
    private  Product product;
    private  Order order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderDetail(int quantity, BigDecimal totalAmount, Product product, Order order) {
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.product = product;
        this.order = order;
    }

    public OrderDetail() {
    }
}
