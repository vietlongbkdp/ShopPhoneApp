package daos;

import models.Product;
import models.ProductImport;
import models.ProductImportDetail;
import services.dto.ProductImportListResponse;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductImportDAO extends  DatabaseConnection{
    public int create(ProductImport productImport) {
        String CREATE = "INSERT INTO `testcase`.`product_imports` (`code`, `import_date`, `total_amount`) " +
                "VALUES (?, ?, ?)";
        String SELECT_MAX_ID = "SELECT MAX(id) as max_id FROM `c0623g1`.`product_imports`";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, productImport.getCode());
            preparedStatement.setDate(2, productImport.getImportDate());
            preparedStatement.setBigDecimal(3, productImport.getTotalAmount());
            preparedStatement.executeUpdate();
            PreparedStatement statementId = connection.prepareStatement(SELECT_MAX_ID);
            var rs = statementId.executeQuery();
            if (rs.next()) {
                return rs.getInt("max_id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ;
        }
        return -1;
    }

    public void createImportDetail(int productImportId, int productId, int quantity, BigDecimal amount) {

        String CREATE = "INSERT INTO `testcase`.`product_import_details` (`quantity`, `amount`, `product_id`, `product_import_id`) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setBigDecimal(2, amount);
            preparedStatement.setInt(3, productId);
            preparedStatement.setInt(4, productImportId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ;
        }
    }

    public List<ProductImportListResponse> findAll() {
        var result = new ArrayList<ProductImportListResponse>();
        String SELECT_ALL = "SELECT pi.id, pi.`code`, pi.import_date, GROUP_CONCAT(p.`name`) products, pi.total_amount FROM " +
                "product_imports pi " +
                "        LEFT JOIN product_import_details pid on pi.id = pid.product_import_id " +
                "LEFT JOIN products p on p.id = pid.product_id GROUP BY pi.id";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(new ProductImportListResponse(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getDate("import_date"),
                        rs.getString("products"),
                        rs.getBigDecimal("total_amount")
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ProductImport findById(int id) {
        String FIND_BY_ID = "SELECT pi.*, pid.id pid_id, pid.product_id p_id, pid.amount, pid.quantity  FROM product_imports as pi " +
                "JOIN product_import_details pid on pid.product_import_id = pi.id WHERE pi.id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            var rs = preparedStatement.executeQuery();
            ProductImport productImport = new ProductImport();
            var productImportDetails = new ArrayList<ProductImportDetail>();
            while (rs.next()) {
                productImport.setCode(rs.getString("code"));
                productImport.setId(rs.getInt("id"));
                productImport.setImportDate(rs.getDate("import_date"));
                productImport.setTotalAmount(rs.getBigDecimal("total_amount"));
                var productImportDetail = new ProductImportDetail();
                productImportDetail.setId(rs.getInt("p_id"));
                productImportDetail.setProduct(new Product(rs.getInt("p_id")));
                productImportDetail.setAmount(rs.getBigDecimal("amount"));
                productImportDetail.setQuantity(rs.getInt("quantity"));
                productImportDetails.add(productImportDetail);

            }
            productImport.setProductImportDetails(productImportDetails);
            return productImport;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void deleteImportDetail(int productImportId) {

        String DELETE_IMPORT_DETAIL = "DELETE FROM `testcase`.`product_import_details` WHERE (`product_import_id` = ?);";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_IMPORT_DETAIL)) {
            preparedStatement.setInt(1, productImportId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProductImport(ProductImport productImport){
        String CREATE = "UPDATE `testcase`.`product_imports` SET `code` = ?, `import_date` = ?, `total_amount` = ? WHERE (`id` = ?);";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, productImport.getCode());
            preparedStatement.setDate(2, productImport.getImportDate());
            preparedStatement.setBigDecimal(3, productImport.getTotalAmount());
            preparedStatement.setInt(4, productImport.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
