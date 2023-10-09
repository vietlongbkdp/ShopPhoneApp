package models;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private   int id;
    private String orderDate;
    private  EStatus status;
    private  User user;
    private  BigDecimal totalAmount;

    private  List<OrderDetail>orderDetails;
    private String shippingCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Order(String orderDate, User user, BigDecimal totalAmount, List<OrderDetail> orderDetails) {
        this.orderDate = orderDate;
        this.user = user;
        this.totalAmount = totalAmount;
        this.orderDetails = orderDetails;
    }

    public Order() {
    }

    public Order(int id) {
        this.id = id;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }
}
