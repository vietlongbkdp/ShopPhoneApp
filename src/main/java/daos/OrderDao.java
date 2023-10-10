package daos;



import com.mysql.cj.xdevapi.PreparableStatement;

import models.EStatus;
import models.Order;
import models.OrderDetail;
import models.Product;
import services.dto.Page;


import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends DatabaseConnection {
    private UserDao userDao;
    private ProductDAO productDAO;

    public OrderDao() {
        userDao = new UserDao();
        productDAO = new ProductDAO();

    }

    public Order findByStatus(String status, int idOrder) {
        String SELECT_BY_STATUS = "SELECT o.* , od.id  od_id , od.quantity od_quantity,od.product_id od_product_id," +
                "od.total_amount od_total_amount FROM orders o JOIN order_details od on o.id=od.order_id WHERE o.status= ? AND o.id= ?; ";
        try (Connection connnection = getConnection();
             PreparedStatement preparedStatement = connnection.prepareStatement(SELECT_BY_STATUS)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, idOrder);
            var rs = preparedStatement.executeQuery();
            Order order = new Order();
            var orderDetails = new ArrayList<OrderDetail>();
            while (rs.next()) {
                order.setId(rs.getInt("id"));
                order.setOrderDate(convertDateTimeFormat(rs.getTimestamp("order_date")));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setStatus(EStatus.valueOf(rs.getString("status")));
                order.setUser(userDao.getUserById(rs.getInt("id_customer")));
                var orderDetail = new OrderDetail();
                orderDetail.setId(rs.getInt("od_id"));
                orderDetail.setQuantity(rs.getInt("od_quantity"));
                orderDetail.setProduct((Product) productDAO.findById(rs.getInt("od_product_id")));
                orderDetail.setTotalAmount(rs.getBigDecimal("od_total_amount"));
                orderDetails.add(orderDetail);
            }
            order.setOrderDetails(orderDetails);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int createOrder(int idUser) {
        String CREATE = "INSERT INTO `bandienthoai`.`orders` (`id_customer`) VALUES (?);";
        String SELECT_MAX_ID = "SELECT MAX(id) as max_id FROM `bandienthoai`.`orders` where id_customer =?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeUpdate();
            PreparedStatement statementId = connection.prepareStatement(SELECT_MAX_ID);
            statementId.setInt(1, idUser);
            var rs = statementId.executeQuery();
            if (rs.next()) {
                return rs.getInt("max_id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public void createOrderDetail(int quantity, int productId, int orderId) {
        String CREATE = "INSERT INTO `bandienthoai`.`order_details` (`quantity`, `product_id`, `order_id`) VALUES (?, ?, ?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Page<Order> findAll(int page, String status) {
        var result = new Page<Order>();
        final int TOTAL_ELEMENT = 6;
        result.setCurrentPage(page);
        var content = new ArrayList<Order>();
        if (status == null) {
            status = "";
        }
        var SELECT_ALL = "SELECT * FROM bandienthoai.orders where status = ?  ;";
        var SELECT_COUNT = "SELECT COUNT(1) cnt FROM bandienthoai.orders where status = ? ;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setString(1, status);
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                content.add(getOrderByResultSet(rs));
            }
            result.setContent(content);
            var preparedStatementCount = connection.prepareStatement(SELECT_COUNT);
            preparedStatement.setString(1, status);
            var rsCount = preparedStatementCount.executeQuery();
            if (rsCount.next()) {
                result.setTotalPage((int) Math.ceil((double) rsCount.getInt("cnt") / TOTAL_ELEMENT));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Order> findAllByUser(String status, int idUser) {
        var result = new ArrayList<Order>();
        var SELECT_ALL = "SELECT * FROM bandienthoai.orders WHERE id_customer = ? AND status = ? ORDER BY id DESC ";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setString(2, status);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(getOrderByResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public List<Order> findAllByStatus(String status) {
        var result = new ArrayList<Order>();
        var SELECT_ALL = "SELECT * FROM bandienthoai.orders WHERE  status = ? ORDER BY id DESC ";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, status);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(getOrderByResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public Page<Order>findAllByUserID(int page,String status, int idUser ){
        var result = new Page<Order>();
        final int TOTAL_ELEMENT = 6;
        result.setCurrentPage(page);
        var content = new ArrayList<Order>();
        if (status == null) {
            status = "";
        }
        var SELECT_ALL = "SELECT * FROM bandienthoai.orders where id_customer = ? and status = ? ";
        var SELECT_COUNT = "SELECT COUNT(1) cnt FROM bandienthoai.orders where id_customer = ? and status = ?;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setString(2, status);
            preparedStatement.setInt(1, idUser);
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                content.add(getOrderByResultSet(rs));
            }
            result.setContent(content);
            var preparedStatementCount = connection.prepareStatement(SELECT_COUNT);
            preparedStatement.setString(2, status);
            preparedStatement.setInt(1, idUser);
            var rsCount = preparedStatementCount.executeQuery();
            if (rsCount.next()) {
                result.setTotalPage((int) Math.ceil((double) rsCount.getInt("cnt") / TOTAL_ELEMENT));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Order getOrderByResultSet(ResultSet rs) throws SQLException {
        var order = new Order();
        order.setId(rs.getInt("id"));
        Timestamp timestamp=rs.getTimestamp("order_date");
        order.setOrderDate(convertDateTimeFormat(rs.getTimestamp("order_date")));
        order.setTotalAmount(rs.getBigDecimal("total_amount"));
        order.setStatus(EStatus.valueOf(rs.getString("status")));
        order.setUser(userDao.getUserById(rs.getInt("id_customer")));
        order.setShippingCode(rs.getString("shipping_code"));
        order.setOrderDetails(findAllOD(order.getId()));
        return order;
    }

    public List<OrderDetail> findAllOD(int idOrder) {
        var result = new ArrayList<OrderDetail>();
        String SELECT = "SELECT * FROM bandienthoai.order_details where order_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {
            preparedStatement.setInt(1, idOrder);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(getOrderDetailByResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private OrderDetail getOrderDetailByResultSet(ResultSet rs) throws SQLException {
        var orderDetail = new OrderDetail();
        orderDetail.setId(rs.getInt("id"));
        orderDetail.setQuantity(rs.getInt("quantity"));
        orderDetail.setTotalAmount(rs.getBigDecimal("total_amount"));
        orderDetail.setProduct(productDAO.findByIdProduct(rs.getInt("product_id")));
        return orderDetail;
    }
    public  String convertDateTimeFormat(Timestamp inputDateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String formattedTimestamp = dateFormat.format(inputDateTime);
        return formattedTimestamp;
    }
    public void updateStatusOrder(String status ,int idOrder){
        String UPDATE = "UPDATE `bandienthoai`.`orders` SET `status` = ? WHERE (`id` = ?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, idOrder);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
