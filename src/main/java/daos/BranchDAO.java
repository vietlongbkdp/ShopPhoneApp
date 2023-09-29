package daos;

import models.Branch;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDAO extends DatabaseConnection {
    public List<Branch> fillAll() {
        List<Branch> results = new ArrayList<>();
        String SELECT_ALL = "SELECT * FROM branchs";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                results.add(new Branch(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }

    public Branch findById(int id) {
        String SELECT_BY_ID = "SELECT * FROM branchs WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Branch(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return null;
    }

}
