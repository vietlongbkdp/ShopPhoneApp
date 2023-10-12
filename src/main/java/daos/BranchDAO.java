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
    public Branch findByName(String  name) {
        String SELECT_BY_ID = "SELECT * FROM branchs WHERE name = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Branch(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return null;
    }

    public void create(String nameBranch) {
        String CREATE_BRANCH = "INSERT INTO `branchs` (`name`) VALUES (?)";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BRANCH);
            preparedStatement.setString(1, nameBranch);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String DELETE_BRANCH = "DELETE FROM `branchs` WHERE (`id` = ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BRANCH);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(int id, String nameBranch) {
        String EDIT_BRANCH = "UPDATE `branchs` SET `name` = ? WHERE (`id` = ?);";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_BRANCH);
            preparedStatement.setString(1, nameBranch);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
