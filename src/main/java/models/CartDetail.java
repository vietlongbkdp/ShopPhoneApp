package models;

import java.math.BigDecimal;

public class CartDetail {
    private  int id;

    private  int quantity;
    private BigDecimal totalAmount;
    private  Cart cart;
    private  Product product;

    public CartDetail(int id, int quantity, BigDecimal totalAmount, Cart cart, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.cart = cart;
        this.product = product;
    }

    public CartDetail() {
    }

    public CartDetail(int id) {
        this.id = id;
    }

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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
