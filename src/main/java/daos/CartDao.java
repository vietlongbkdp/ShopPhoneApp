package daos;

import models.Cart;
import models.CartDetail;
import models.Product;
import models.User;
import services.dto.ProductImportListResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao extends DatabaseConnection {
    private ProductDAO productDAO;

    public CartDao() {
        productDAO = new ProductDAO();
    }

    public Cart findByUserId(int id) {

        String SELECT_BY_USER_ID = "SELECT c.*, cd.id AS cd_id, cd.product_id AS p_id, cd.quantity, cd.total_amount AS cd_total_amount, cd.checked as cd_checked , " +
                "(SELECT SUM(total_amount) FROM cart_details WHERE cart_id = c.id) AS c_total_amount " +
                "FROM bandienthoai.carts c JOIN cart_details cd ON c.id = cd.cart_id WHERE c.user_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USER_ID)) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            var rs = preparedStatement.executeQuery();
            Cart cart = new Cart();
            var cartDetails = new ArrayList<CartDetail>();
            while (rs.next()) {
                cart.setId(rs.getInt("id"));
                cart.setTotalAmount(rs.getBigDecimal("c_total_amount"));
                cart.setUser(new User(rs.getInt("user_id")));
                var cartDetail = new CartDetail();
                cartDetail.setId(rs.getInt("cd_id"));
                cartDetail.setProduct((Product) productDAO.findById(rs.getInt("p_id")));
                cartDetail.setQuantity(rs.getInt("quantity"));
                cartDetail.setTotalAmount(rs.getBigDecimal("cd_total_amount"));
                cartDetail.setChecked(rs.getInt("cd_checked"));
                cartDetails.add(cartDetail);
            }
            cart.setCartDetails(cartDetails);
            return cart;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int findCartId(int userId) {
        String SELECT_BY_USER_ID = "SELECT id FROM bandienthoai.carts where user_id=?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USER_ID)) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, userId);
            var rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public void createCart(int id) {
        String CREATE = "INSERT INTO `bandienthoai`.`carts` (`user_id`) VALUES (?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createCartDetail(int cartId, int productId) {
        String CREATE = "INSERT INTO `bandienthoai`.`cart_details` (`cart_id`, `product_id`, `quantity`) VALUES (?, ?, '1');";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCartDetail(int cartId, int productId, int quantity) {
        String UPDATE = "UPDATE `bandienthoai`.`cart_details` SET `quantity` = ? WHERE (cart_id = ?) AND (product_id=?) ;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, cartId);
            preparedStatement.setInt(3, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCartDetail(int cartDetailId) {
        String DELETE = "DELETE FROM `bandienthoai`.`cart_details` WHERE (`id` = ?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, cartDetailId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAllCartDetailByCartID(int cartId) {
        String DELETE = "DELETE FROM `bandienthoai`.`cart_details` WHERE (`cart_id` = ?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, cartId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCartDetail(int cartDetailId, int quantity) {
        String UPDATE = "UPDATE `bandienthoai`.`cart_details` SET `quantity` = ? WHERE id=? ;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, cartDetailId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCartDetailChecked(int cartDetailId, int quantity, int checked) {
        String UPDATE = "UPDATE `bandienthoai`.`cart_details` SET `quantity` = ?, `checked` = ? WHERE (`id` = ?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, checked);
            preparedStatement.setInt(3, cartDetailId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<CartDetail> findListCartDetailByCartID(int cartId) {
        var result = new ArrayList<CartDetail>();
        String SELECT = "SELECT * FROM bandienthoai.cart_details where cart_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, cartId);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(new CartDetail(
                        rs.getInt("id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("total_amount"),
                        new Cart(rs.getInt("cart_id")),
                        new Product(rs.getInt("product_id"))
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<CartDetail> findCartByCartIdChecked(int cartId, int checked) {
        var result = new ArrayList<CartDetail>();
        var SELECT = "SELECT * FROM bandienthoai.cart_details where cart_id=? and checked=?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {
            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, checked);
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(getCartDetailByRs(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private CartDetail getCartDetailByRs(ResultSet rs) throws SQLException {
        Product product = (Product) productDAO.findById(rs.getInt("product_id"));
        var cartDetail = new CartDetail();
        cartDetail.setId(rs.getInt("id"));
        cartDetail.setCart(new Cart(rs.getInt("cart_id")));
        cartDetail.setProduct(product);
        cartDetail.setQuantity(rs.getInt("quantity"));
        cartDetail.setTotalAmount(rs.getBigDecimal("total_amount"));
        cartDetail.setChecked(rs.getInt("checked"));
        return cartDetail;
    }
}
