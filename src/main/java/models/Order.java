package models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Order {
    private   int id;
    private  Date orderDate;
    private  EStatus status;
    private  User user;
    private  BigDecimal totalAmount;

    private  List<OrderDetail>orderDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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

    public Order(Date orderDate, User user, BigDecimal totalAmount, List<OrderDetail> orderDetails) {
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
}
