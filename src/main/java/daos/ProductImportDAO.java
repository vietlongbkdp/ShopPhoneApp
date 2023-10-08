package daos;

import models.Product;
import models.ProductImport;
import models.ProductImportDetail;
import services.dto.Page;
import services.dto.ProductImportListResponse;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductImportDAO extends  DatabaseConnection{
    public int create(ProductImport productImport) {
        String CREATE = "INSERT INTO `bandienthoai`.`product_imports` (`code`, `import_date`, `total_amount`) " +
                "VALUES (?, ?, ?)";
        String SELECT_MAX_ID = "SELECT MAX(id) as max_id FROM `bandienthoai`.`product_imports`";
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

    public void createImportDetail(int productImportId, int productId, int quantity, BigDecimal totalAmount) {

        String CREATE = "INSERT INTO `bandienthoai`.`product_import_details` (`quantity`, `price_import`, `product_id`, `product_import_id`) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setBigDecimal(2, totalAmount);
            preparedStatement.setInt(3, productId);
            preparedStatement.setInt(4, productImportId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ;
        }
    }

    public Page<ProductImportListResponse> findAll(int page, String search) {
        var result = new Page<ProductImportListResponse>();
        final int TOTAL_ELEMENT = 5;
        result.setCurrentPage(page);
        var content = new ArrayList<ProductImportListResponse>();
        if (search == null) {
            search = "";
        }
        search = "%" + search.toLowerCase() + "%";
        String SELECT_ALL = "SELECT pi.id, pi.code, pi.import_date, GROUP_CONCAT(p.productName) AS products, pi.total_amount\n" +
                " FROM product_imports pi\n" +
                " LEFT JOIN product_import_details pid ON pi.id = pid.product_import_id  \n" +
                " LEFT JOIN products p ON p.id = pid.product_id\n" +
                " WHERE pi.deleted = 0  AND (  LOWER(p.productName) LIKE ?  OR LOWER(pi.code) LIKE ?) \n" +
                " GROUP BY pi.id  limit ? offset ? ;";
        String SELECT_COUNT = "SELECT COUNT(*) AS cnt\n" +
                "FROM (\n" +
                "    SELECT pi.id, pi.code, pi.import_date, GROUP_CONCAT(p.productName) AS products, pi.total_amount\n" +
                "    FROM product_imports pi\n" +
                "    LEFT JOIN product_import_details pid ON pi.id = pid.product_import_id  \n" +
                "    LEFT JOIN products p ON p.id = pid.product_id\n" +
                "    WHERE pi.deleted = 0 AND (LOWER(p.productName) LIKE ? OR LOWER(pi.code) LIKE ?)  \n" +
                "    GROUP BY pi.id, pi.code, pi.import_date, pi.total_amount\n" +
                ") AS subquery;";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, search);
            preparedStatement.setString(2, search);
            preparedStatement.setInt(3, TOTAL_ELEMENT);
            preparedStatement.setInt(4, (page - 1) * TOTAL_ELEMENT);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                content.add(new ProductImportListResponse(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getDate("import_date"),
                        rs.getString("products"),
                        rs.getBigDecimal("total_amount")
                ));
            }
            result.setContent(content);
            var preparedStatementCount = connection.prepareStatement(SELECT_COUNT);
            preparedStatementCount.setString(1, search);
            preparedStatementCount.setString(2, search);
            var rsCount = preparedStatementCount.executeQuery();
            if (rsCount.next()) {
                result.setTotalPage((int) Math.ceil((double) rsCount.getInt("cnt") / TOTAL_ELEMENT));
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

        String DELETE_IMPORT_DETAIL = "DELETE FROM `bandienthoai`.`product_import_details` WHERE (`product_import_id` = ?);";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_IMPORT_DETAIL)) {
            preparedStatement.setInt(1, productImportId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void updateProductImport(ProductImport productImport){
        String CREATE = "UPDATE `candycake`.`product_imports` SET `code` = ?, `import_date` = ?, `total_amount` = ? WHERE (`id` = ?);";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, productImport.getCode());
            preparedStatement.setDate(2, productImport.getImportDate());
            preparedStatement.setBigDecimal(3, productImport.getTotalAmount());
            preparedStatement.setInt(4, productImport.getId());
            System.out.println(productImport);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ProductImportDetail getQuantityByIdProduct(int id) {
        var result = new ProductImportDetail();
        result.setId(id);
        String GET_PRODUCT_QUANTITY ="SELECT * FROM bandienthoai.product_import_details where id=?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_QUANTITY)) {
            preparedStatement.setInt(1, id);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.setQuantity(rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}
