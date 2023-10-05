package daos;

import com.mysql.cj.xdevapi.PreparableStatement;
import models.EStatus;
import models.Order;
import models.OrderDetail;
import models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDao extends DatabaseConnection {
    private UserDao userDao;
    private ProductDAO productDAO;

    public OrderDao() {
        userDao = new UserDao();
        productDAO= new ProductDAO();
    }

    public Order findByStatus(String status) {
        String SELECT_BY_STATUS = "SELECT o.* , od.id  od_id , od.quantity od_quantity,od.product_id od_product_id," +
                "od.total_amount od_total_amount FROM orders o JOIN order_details od on o.id=od.order_id WHERE o.status= ? ; ";
        try (Connection connnection = getConnection();
             PreparedStatement preparedStatement = connnection.prepareStatement(SELECT_BY_STATUS)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, status);
            var rs = preparedStatement.executeQuery();
            Order order = new Order();
            var orderDetails = new ArrayList<OrderDetail>();
            while (rs.next()) {
                order.setId(rs.getInt("id"));
                order.setOrderDate(rs.getDate("order_Date"));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setStatus(EStatus.valueOf(rs.getString("status")));
                order.setUser(userDao.getUserById(rs.getInt("id_customer")));
                var orderDetail= new OrderDetail();
                orderDetail.setId(rs.getInt("od_id"));
                orderDetail.setQuantity(rs.getInt("od_quantity"));
                orderDetail.setProduct((Product)productDAO.findById(rs.getInt("od_product_id")));
                orderDetail.setTotalAmount(rs.getBigDecimal("od_total_amount"));
                orderDetails.add(orderDetail);
            }
            order.setOrderDetails(orderDetails);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
