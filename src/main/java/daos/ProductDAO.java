package daos;

import models.Branch;
import models.Product;
import services.dto.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO extends DatabaseConnection {
    public Object findById(int id) {
        var SELECT_BY_ID = "SELECT p.*, c.name category_name " + "FROM products p JOIN categories c on " + "c.id = p.category_id " + "WHERE p.id = ? and p.deleted = '0'";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return getProductByResultSet(rs);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Object findAll(int page, boolean isShowRestore, String search) {
        var result = new Page<Product>();
        final int TOTAL_ELEMENT = 6;
        result.setCurrentPage(page);
        var content = new ArrayList<Product>();
        if (search == null) {
            search = "";
        }
        search = "%" + search.toLowerCase() + "%";
        final var DELETED = isShowRestore ? 1 : 0;
        var SELECT_ALL = "SELECT p.*, b.name AS branch_name " +
                "FROM products p " +
                "JOIN branchs b ON b.id = p.branch " +
                "WHERE " +
                "    (p.deleted = 1)  " +
                "    AND ( " +
                "        LOWER(p.productName) LIKE ?  " +
                "        OR LOWER(b.name) LIKE ?  " +
                "        OR LOWER(p.ram) LIKE ?  " +
                "        OR LOWER(p.operatingSystem) LIKE ? " +
                "    ) " +
                "LIMIT ? OFFSET ?;";
        var SELECT_COUNT = "SELECT COUNT(1) cnt FROM products p " +
                "JOIN branchs b on b.id = p.branch WHERE p.deleted = ? And " +
                "(Lower(p.productName) like ? or lower(b.name) like ? or lower(p.ram) like ? or lower(p.operatingSystem) like ?) ";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setInt(1, DELETED);
            preparedStatement.setString(2, search);
            preparedStatement.setString(3, search);
            preparedStatement.setString(4, search);
            preparedStatement.setInt(5, TOTAL_ELEMENT);
            preparedStatement.setInt(6, (page - 1) * TOTAL_ELEMENT);
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                content.add(getProductByResultSet(rs));
            }
            result.setContent(content);
            var preparedStatementCount = connection.prepareStatement(SELECT_COUNT);
            preparedStatementCount.setInt(1, DELETED);
            preparedStatementCount.setString(2, search);
            preparedStatementCount.setString(3, search);
            preparedStatementCount.setString(4, search);
            var rsCount = preparedStatementCount.executeQuery();
            if (rsCount.next()) {
                result.setTotalPage((int) Math.ceil((double) rsCount.getInt("cnt") / TOTAL_ELEMENT));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void update(Product product) {
        String UPDATE = "UPDATE `testcase`.`products` " + "SET `productName` = ?, `branchName` = ?, `image` = ?, `price` = ?, `quantity` = ?, `warrantyPeriod` = ?, `ram` = ?, `size` = ?, `color` = ?, `camera` = ?, `operatingSystem` = ?, `pin` = ? " + "WHERE (`id` = ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getBranch().getId());
            preparedStatement.setString(3, product.getImage());
            preparedStatement.setString(4, product.getPrice());
            preparedStatement.setString(5, product.getQuantity());
            preparedStatement.setString(6, product.getWarrantyPeriod());
            preparedStatement.setString(7, product.getRam());
            preparedStatement.setString(8, product.getSize());
            preparedStatement.setString(9, product.getColor());
            preparedStatement.setString(10, product.getCamera());
            preparedStatement.setString(11, product.getOperatingSystem());
            preparedStatement.setString(12, product.getPin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void restore(int id) {
        String DELETE = "UPDATE `testcase`.`products` " + "SET `deleted` = '0' " + "WHERE (`id` = ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String DELETE = "UPDATE `testcase`.`products` " + "SET `deleted` = '1' " + "WHERE (`id` = ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void create(Product product) {
        System.out.println("aaaaaa");
        String CREATE = "INSERT INTO `testcase`.`products` (`productName`, `branch`, `image`, `price`, `quantity`, " +
                "`warrantyPeriod`, `ram`, `size`, `color`, `camera`, `operatingSystem`, `pin`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getBranch().getId());
            preparedStatement.setString(3, product.getImage());
            preparedStatement.setString(4, product.getPrice());
            preparedStatement.setString(5, product.getQuantity());
            preparedStatement.setString(6, product.getWarrantyPeriod());
            preparedStatement.setString(7, product.getRam());
            preparedStatement.setString(8, product.getSize());
            preparedStatement.setString(9, product.getColor());
            preparedStatement.setString(10, product.getCamera());
            preparedStatement.setString(11, product.getOperatingSystem());
            preparedStatement.setString(12, product.getPin());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Product getProductByResultSet(ResultSet rs) throws SQLException {
        var product = new Product();
        product.setProductName(rs.getString("productName"));
        product.setBranch(new Branch(rs.getInt("branch"), rs.getString("branch_name")));
        product.setImage(rs.getString("image"));
        product.setPrice(rs.getString("price"));
        product.setQuantity(rs.getString("quantity"));
        product.setWarrantyPeriod(rs.getString("warrantyPeriod"));
        product.setRam(rs.getString("ram"));
        product.setSize(rs.getString("size"));
        product.setColor(rs.getString("color"));
        product.setCamera(rs.getString("camera"));
        product.setOperatingSystem(rs.getString("operatingSystem"));
        product.setPin(rs.getString("pin"));
        return product;
    }
}
