package daos;

import models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends DatabaseConnection{
    public List<Role> getAllRole(){
        List<Role> roleList = new ArrayList<>();
        String SELECT_ALL_ROLE = "SELECT * FROM `role`";

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROLE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                roleList.add(new Role(resultSet.getInt("id"), resultSet.getString("role_Name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roleList;
    }
    public Role getRoleById(int id){
        String SELECT_ROLE_BY_ID = "SELECT * FROM `role` WHERE id= ?";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new Role(resultSet.getInt("id"), resultSet.getString("role_Name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
