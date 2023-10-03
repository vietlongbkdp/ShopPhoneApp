package models;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
    private int id;
    private   User user;
    private   List<CartDetail> cartDetails;
    private  BigDecimal totalAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Cart(int id, User user, List<CartDetail> cartDetails, BigDecimal totalAmount) {
        this.id = id;
        this.user = user;
        this.cartDetails = cartDetails;
        this.totalAmount = totalAmount;
    }

    public Cart() {
    }

    public Cart(int id) {
        this.id = id;
    }
}
