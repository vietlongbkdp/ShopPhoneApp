package daos;

import models.Branch;
import models.EPriceRange;
import models.Product;
import services.dto.AllProductDto;
import services.dto.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DatabaseConnection {
    private BranchDAO branchDAO;

    public ProductDAO() {
        branchDAO = new BranchDAO();
    }

    public Product findById(int id) {
        var SELECT_BY_ID = "SELECT * FROM bandienthoai.products where id = ? and deleted = '0'";
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

    public Product findByIdProduct(int id) {
        var SELECT_BY_ID = "SELECT p.*, b.name branch_name " + "FROM products p JOIN branchs b on " + "b.id = p.branch_id " + "WHERE p.id = ? and p.deleted = '0'";
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
        final int TOTAL_ELEMENT = 12;
        result.setCurrentPage(page);
        var content = new ArrayList<Product>();
        if (search == null) {
            search = "";
        }
        search = "%" + search.toLowerCase() + "%";
        final var DELETED = isShowRestore ? 1 : 0;
        var SELECT_ALL = "SELECT p.*, b.name AS branch_name " +
                "FROM products p " +
                "JOIN branchs b ON b.id = p.branch_id " +
                "WHERE " +
                "    (p.deleted = ?)  " +
                "    AND ( " +
                "        LOWER(p.productName) LIKE ?  " +
                "        OR LOWER(b.name) LIKE ?  " +
                "    ) " +
                "LIMIT ? OFFSET ?;";
        var SELECT_COUNT = "SELECT COUNT(1) cnt FROM products p " +
                "JOIN branchs b on b.id = p.branch_id WHERE p.deleted = ? And " +
                "(Lower(p.productName) like ? or lower(b.name) like ?) ";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setInt(1, DELETED);
            preparedStatement.setString(2, search);
            preparedStatement.setString(3, search);
            preparedStatement.setInt(4, TOTAL_ELEMENT);
            preparedStatement.setInt(5, (page - 1) * TOTAL_ELEMENT);
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                content.add(getProductByRs(rs));
            }
            result.setContent(content);
            var preparedStatementCount = connection.prepareStatement(SELECT_COUNT);
            preparedStatementCount.setInt(1, DELETED);
            preparedStatementCount.setString(2, search);
            preparedStatementCount.setString(3, search);
            var rsCount = preparedStatementCount.executeQuery();
            if (rsCount.next()) {
                result.setTotalPage((int) Math.ceil((double) rsCount.getInt("cnt") / TOTAL_ELEMENT));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Product> findAll() {
        var content = new ArrayList<Product>();
        var SELECT_ALL = "SELECT p.*, b.name AS branch_name " +
                "FROM products p " +
                "JOIN branchs b ON b.id = p.branch_id ";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                content.add(getProductByResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return content;
    }

    public List<Product> findAllProduct(boolean isShowRestore) {
        var content = new ArrayList<Product>();
        final var DELETED = isShowRestore ? 1 : 0;
        var SELECT_ALL = "SELECT p.*, b.name AS branch_name " +
                "FROM products p " +
                "JOIN branchs b ON b.id = p.branch_id " +
                "WHERE " +
                "    (p.deleted = ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, DELETED);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                content.add(getProductByResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return content;
    }

    public Branch findBranchByID(int id) {
        var SELECT_BY_ID = "SELECT * FROM bandienthoai.branchs where  id =?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return getBranchByRs(rs);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Branch getBranchByRs(ResultSet rs) throws SQLException {
        var branch = new Branch();
        branch.setId(rs.getInt("id"));
        branch.setName(rs.getString(" name"));
        return branch;
    }

    private Product getProductByResultSet(ResultSet rs) throws SQLException {
        var product = new Product();
        product.setId(rs.getInt("id"));
        product.setProductName(rs.getString("productName"));
        product.setBranch(findBranchByID(rs.getInt("branch_id")));
        product.setImage(rs.getString("image"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setQuantity(rs.getInt("quantity"));
        product.setWarrantyPeriod(rs.getString("warrantyPeriod"));
        product.setRam(rs.getString("ram"));
        product.setSize(rs.getString("size"));
        product.setColor(rs.getString("color"));
        product.setCamera(rs.getString("camera"));
        product.setOperatingSystem(rs.getString("operatingSystem"));
        product.setPin(rs.getString("pin"));
        product.setePriceRange(EPriceRange.valueOf(rs.getString("price_range")));
        return product;
    }


    public void update(Product product) {
        String UPDATE = "UPDATE `bandienthoai`.`products` " + "SET `productName` = ?, `branch_id` = ?, `image` = ?, `price` = ?, `warrantyPeriod` = ?, `ram` = ?, `size` = ?, `color` = ?, `camera` = ?, `operatingSystem` = ?, `pin` = ? " + "WHERE (`id` = ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getBranch().getId());
            preparedStatement.setString(3, product.getImage());
            preparedStatement.setBigDecimal(4, product.getPrice());
            preparedStatement.setString(5, product.getWarrantyPeriod());
            preparedStatement.setString(6, product.getRam());
            preparedStatement.setString(7, product.getSize());
            preparedStatement.setString(8, product.getColor());
            preparedStatement.setString(9, product.getCamera());
            preparedStatement.setString(10, product.getOperatingSystem());
            preparedStatement.setString(11, product.getPin());
            preparedStatement.setInt(12, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void restore(int id) {
        String DELETE = "UPDATE `bandienthoai`.`products` " + "SET `deleted` = '0' " + "WHERE (`id` = ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String DELETE = "UPDATE `bandienthoai`.`products` " + "SET `deleted` = '1' " + "WHERE (`id` = ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void create(Product product) {
        String CREATE = "INSERT INTO `bandienthoai`.`products` (`productName`, `branch_id`, `image`, `price`, " +
                "`warrantyPeriod`, `ram`, `size`, `color`, `camera`, `operatingSystem`, `pin`  ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getBranch().getId());
            preparedStatement.setString(3, product.getImage());
            preparedStatement.setBigDecimal(4, product.getPrice());
            preparedStatement.setString(5, product.getWarrantyPeriod());
            preparedStatement.setString(6, product.getRam());
            preparedStatement.setString(7, product.getSize());
            preparedStatement.setString(8, product.getColor());
            preparedStatement.setString(9, product.getCamera());
            preparedStatement.setString(10, product.getOperatingSystem());
            preparedStatement.setString(11, product.getPin());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<AllProductDto> findAllProductDto(boolean isShowRestore) {
        var content = new ArrayList<AllProductDto>();
        final var DELETED = isShowRestore ? 1 : 0;
        var SELECT_ALL = "SELECT p.id, p.productName, p.quantity, b.name AS branchName, p.image, p.price\n" +
                "FROM products p \n" +
                "JOIN branchs b ON b.id = p.branch_id \n" +
                "WHERE   (p.deleted = ?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, DELETED);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                content.add(getProductDtoByResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return content;
    }


    private AllProductDto getProductDtoByResultSet(ResultSet rs) throws SQLException {
        var product = new AllProductDto();
        product.setId(rs.getInt("id"));
        product.setProductName(rs.getString("productName"));
        product.setQuantity(rs.getInt("quantity"));
        product.setBranchName(rs.getString("branchName"));
        product.setUrlImage(rs.getString("image"));
        product.setPrice(rs.getBigDecimal("price"));
        return product;
    }

    public Page<Product> findAllProduct(int page, boolean isShowRestore, String search, String ePriceRange, String branchName) {
        var result = new Page<Product>();
        final int TOTAL_ELEMENT = 6;
        result.setCurrentPage(page);
        var content = new ArrayList<Product>();
        if (search == null) {
            search = "";
        }
        if (ePriceRange == null) {
            ePriceRange = "%%";
        }
        if (branchName == null) {
            branchName = "%%";
        }

        search = "%" + search.toLowerCase() + "%";
        final var DELETED = isShowRestore ? 1 : 0;
        var SELECT = "SELECT p.*, b.name AS branch_name\n" +
                "FROM products p\n" +
                "JOIN branchs b ON b.id = p.branch_id\n" +
                "WHERE p.deleted = ?\n" +
                "  AND b.name = ?\n" +
                "  AND (\n" +
                "    LOWER(p.productName) LIKE ?\n" +
                "    OR LOWER(b.name) LIKE ?\n" +
                "  ) AND p.price_range = ?  " +
                " LIMIT ? OFFSET ?";

        var SELECT_COUNT = "SELECT COUNT(1) cnt " + "FROM products p\n" +
                "JOIN branchs b ON b.id = p.branch_id\n" +
                "WHERE p.deleted = ?\n" +
                "  AND b.name = ?\n" +
                "  AND (\n" +
                "    LOWER(p.productName) LIKE ?\n" +
                "    OR LOWER(b.name) LIKE ?\n" +
                "  ) AND p.price_range = ?  ";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {
            preparedStatement.setInt(1, DELETED);
            preparedStatement.setString(2, branchName);
            preparedStatement.setString(3, search);
            preparedStatement.setString(3, search);
            preparedStatement.setString(5, ePriceRange);
            preparedStatement.setInt(6, TOTAL_ELEMENT);
            preparedStatement.setInt(7, (page - 1) * TOTAL_ELEMENT);
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                content.add(getProductByRs(rs));
                result.setContent(content);
                var preparedStatementCount = connection.prepareStatement(SELECT_COUNT);
                preparedStatement.setInt(1, DELETED);
                preparedStatement.setString(2, branchName);
                preparedStatement.setString(3, search);
                preparedStatement.setString(3, search);
                preparedStatement.setString(5, ePriceRange);
                var rsCount = preparedStatementCount.executeQuery();
                if (rsCount.next()) {
                    result.setTotalPage((int) Math.ceil((double) rsCount.getInt("cnt") / TOTAL_ELEMENT));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Page<Product> findAllProductIfNULL(int page, boolean isShowRestore, String search, String ePriceRange, String branchName) {
        var result = new Page<Product>();
        final int TOTAL_ELEMENT = 6;
        result.setCurrentPage(page);
        var content = new ArrayList<Product>();
        if (search == null) {
            search = "";
        }
        if (ePriceRange == null) {
            ePriceRange = "%%";
        }
        if (branchName == null) {
            branchName = "%%";
        }

        search = "%" + search.toLowerCase() + "%";
        final var DELETED = isShowRestore ? 1 : 0;
        var SELECT = "SELECT p.*, b.name AS branch_name\n" +
                "FROM products p\n" +
                "JOIN branchs b ON b.id = p.branch_id\n" +
                "WHERE p.deleted = ?\n" +
                "  AND (\n" +
                "    LOWER(p.productName) LIKE ?\n" +
                "    OR LOWER(b.name) LIKE ?\n" +
                "  ) OR p.price_range = ? " +
                " LIMIT ? OFFSET ?";
        var SELECT_COUNT = "SELECT COUNT(1) cnt " + "FROM products p\n" +
                "JOIN branchs b ON b.id = p.branch_id\n" +
                "WHERE p.deleted = ?\n" +
                "  AND b.name = ?\n" +
                "  AND (\n" +
                "    LOWER(p.productName) LIKE ?\n" +
                "    OR LOWER(b.name) LIKE ?\n" +
                "  ) OR p.price_range = ? ";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {
            preparedStatement.setInt(1, DELETED);
            preparedStatement.setString(2, search);
            preparedStatement.setString(3, search);
            preparedStatement.setString(4, ePriceRange);
            preparedStatement.setInt(5, TOTAL_ELEMENT);
            preparedStatement.setInt(6, (page - 1) * TOTAL_ELEMENT);
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                content.add(getProductByRs(rs));
                result.setContent(content);
                var preparedStatementCount = connection.prepareStatement(SELECT_COUNT);
                preparedStatement.setInt(1, DELETED);
                preparedStatement.setString(2, branchName);
                preparedStatement.setString(3, search);
                preparedStatement.setString(3, search);
                preparedStatement.setString(5, ePriceRange);
                var rsCount = preparedStatementCount.executeQuery();
                if (rsCount.next()) {
                    result.setTotalPage((int) Math.ceil((double) rsCount.getInt("cnt") / TOTAL_ELEMENT));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Product getProductByRs(ResultSet rs) throws SQLException {
        var product = new Product();
        product.setId(rs.getInt("id"));
        product.setProductName(rs.getString("productName"));
        product.setBranch(branchDAO.findById(rs.getInt("branch_id")));
        product.setImage(rs.getString("image"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setQuantity(rs.getInt("quantity"));
        product.setWarrantyPeriod(rs.getString("warrantyPeriod"));
        product.setRam(rs.getString("ram"));
        product.setSize(rs.getString("size"));
        product.setColor(rs.getString("color"));
        product.setCamera(rs.getString("camera"));
        product.setOperatingSystem(rs.getString("operatingSystem"));
        product.setPin(rs.getString("pin"));
        product.setePriceRange(EPriceRange.valueOf(rs.getString("price_range")));
        return product;
    }

    public List<Branch> findAllBranch() {
        var content = new ArrayList<Branch>();
        var SELECT_ALL = "SELECT * FROM bandienthoai.branchs;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                content.add(getBranchByResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return content;
    }

    private Branch getBranchByResultSet(ResultSet rs) throws SQLException {
        var branch = new Branch();
        branch.setId(rs.getInt("id"));
        branch.setName(rs.getString("name"));
        return branch;
    }

    public List<Product> findProductBestSeller(int limit) {
        var content = new ArrayList<Product>();
        String SELECT = "SELECT products.*, SUM(product_import_details.quantity) - products.quantity AS sold_quantity\n" +
                "                FROM product_import_details\n" +
                "                JOIN products ON products.id = product_import_details.product_id\n" +
                "                WHERE products.deleted = 0\n" +
                "                GROUP BY products.id\n" +
                "                ORDER BY sold_quantity DESC limit ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {
            preparedStatement.setInt(1, limit);
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                content.add(getProductByRs(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return content;
    }

    public Page<Product> findAll(int page, String search, String ePriceRange, String branchName) {
        var result = new Page<Product>();
        final int TOTAL_ELEMENT = 12;
        result.setCurrentPage(page);
        var content = new ArrayList<Product>();
        if(search==null){
            search="";
        }

        if(ePriceRange==null|| ePriceRange.equals("DEFAULT")){
            ePriceRange="";
        }

        if(branchName==null){
            branchName="";
        }
        search = "%" + search.toLowerCase() + "%";

        var SEARCH_QUERY = "SELECT p.*, b.name AS branch_name \n" +
                "FROM products p\n" +
                "JOIN branchs b ON b.id = p.branch_id\n" +
                "WHERE p.deleted = 0 \n";
        var SELECT_COUNT_QUERY = "SELECT COUNT(1) cnt " + "FROM products p\n" +
                "JOIN branchs b ON b.id = p.branch_id\n" +
                "WHERE p.deleted = 0 \n";
        if (search.equals("%%")) {
            if (!ePriceRange.isEmpty()) {
                SEARCH_QUERY += " AND p.price_range = " + "'"+ePriceRange+ "'";
                SELECT_COUNT_QUERY += " AND p.price_range = " + "'"+ePriceRange+ "'";
            }
            if (!branchName.isEmpty()) {
                SEARCH_QUERY += " AND b.name = " + "'"+branchName+ "'";
                SELECT_COUNT_QUERY += " AND b.name = " + "'"+branchName+ "'";
            }
        } else  {
            SEARCH_QUERY += " AND ( LOWER(p.productName) LIKE " + "'"+search+ "'" + " OR LOWER(b.name) LIKE " + "'"+search+ "'" + " OR LOWER(p.price) LIKE " + "'"+search+ "'" + ")";
            SELECT_COUNT_QUERY += " AND ( LOWER(p.productName) LIKE " + "'"+search+ "'" + " OR LOWER(b.name) LIKE " + "'"+search+ "'" + " OR LOWER(p.price) LIKE " + "'"+search+ "'" + ")";

            if (!ePriceRange.isEmpty()) {
                SEARCH_QUERY += " AND p.price_range = " + "'"+ePriceRange+ "'";
                SELECT_COUNT_QUERY += " AND p.price_range = " + "'"+ePriceRange+ "'";
            }
            if (!branchName.isEmpty()) {
                SEARCH_QUERY += " AND b.name = " + "'"+branchName+ "'";
                SELECT_COUNT_QUERY += " AND b.name = " + "'"+branchName+ "'";
            }
        }
        SEARCH_QUERY += " LIMIT " + TOTAL_ELEMENT + " OFFSET " + (page - 1) * TOTAL_ELEMENT;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_QUERY)) {
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                content.add(getProductByRs(rs));
                result.setContent(content);
                var preparedStatementCount = connection.prepareStatement(SELECT_COUNT_QUERY);
                var rsCount = preparedStatementCount.executeQuery();
                if (rsCount.next()) {
                    result.setTotalPage((int) Math.ceil((double) rsCount.getInt("cnt") / TOTAL_ELEMENT));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}