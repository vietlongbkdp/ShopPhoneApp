package daos;

import models.Cart;
import models.CartDetail;
import models.Product;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDao extends DatabaseConnection {
    public Cart findByUserId(int id) {
        String SELECT_BY_USER_ID = "SELECT c.* ,cd.id cd_id, cd.product_id p_id, cd.quantity,cd.total_amount cd_total_amount " +
                "FROM bandienthoai.carts c JOIN cart_details cd on c.id=cd.cart_id WHERE c.user_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USER_ID)) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            var rs = preparedStatement.executeQuery();
            Cart cart = new Cart();
            var cartDetails = new ArrayList<CartDetail>();
            while (rs.next()) {
                cart.setId(rs.getInt("id"));
                cart.setTotalAmount(rs.getBigDecimal("total_amount"));
                cart.setUser(new User(rs.getInt("user_id")));
                var cartDetail = new CartDetail();
                cartDetail.setId(rs.getInt("cd_id"));
                cartDetail.setProduct(new Product(rs.getInt("p_id")));
                cartDetail.setQuantity(rs.getInt("quantity"));
                cartDetail.setTotalAmount(rs.getBigDecimal("cd_total_amount"));
                cartDetails.add(cartDetail);
            }
            cart.setCartDetails(cartDetails);
            return cart;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void createCart(int id){
        String CREATE ="INSERT INTO `bandienthoai`.`carts` (`user_id`) VALUES (?)";
        try (Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(CREATE)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
