package daos;

import utils.StringUtil;
import models.EStatus;
import models.Order;
import models.OrderDetail;
import models.Product;
import services.dto.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                order.setOrderDate(StringUtil.convertDateTimeFormat(rs.getString("order_Date")));
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

    public void createOrder(int idUser) {
        String CREATE = "INSERT INTO `bandienthoai`.`orders` (`id_customer`) VALUES (?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, idUser);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void createOrderDetail(int quantity, int productId, int orderId){
        String CREATE = "INSERT INTO `bandienthoai`.`order_details` (`quantity`, `product_id`, `order_id`) VALUES (?, ?, ?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, productId);
            preparedStatement.setInt(2, orderId);
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
        var SELECT_ALL = "SELECT o.* , od.id  od_id , od.quantity od_quantity,od.product_id od_product_id," +
                "od.total_amount od_total_amount FROM orders o JOIN order_details od on o.id=od.order_id WHERE o.status= ? ;";
        var SELECT_COUNT = "SELECT COUNT(1) cnt FROM orders o JOIN order_details od on o.id=od.order_id WHERE o.status= ?;";
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

    private Order getOrderByResultSet(ResultSet rs) throws SQLException {
        var order = new Order();
        var orderDetails = new ArrayList<OrderDetail>();
        order.setId(rs.getInt("id"));
        order.setOrderDate(StringUtil.convertDateTimeFormat(rs.getString("order_Date")));
        order.setTotalAmount(rs.getBigDecimal("total_amount"));
        order.setStatus(EStatus.valueOf(rs.getString("status")));
        order.setUser(userDao.getUserById(rs.getInt("id_customer")));
        var orderDetail = new OrderDetail();
        orderDetail.setId(rs.getInt("od_id"));
        orderDetail.setQuantity(rs.getInt("od_quantity"));
        orderDetail.setProduct((Product) productDAO.findById(rs.getInt("od_product_id")));
        orderDetail.setTotalAmount(rs.getBigDecimal("od_total_amount"));
        orderDetails.add(orderDetail);
        return order;
    }
}
