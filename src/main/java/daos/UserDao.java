package daos;

import models.EGender;
import models.Role;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserDao extends DatabaseConnection{
    RoleDao roleDao = new RoleDao();
    public List<User> getAllUser(){
        List<User> userList = new ArrayList<>();
        String SELECT_ALL_USER = "SELECT * FROM `user`";

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               User user = new User();
               user.setId(resultSet.getInt("id"));
               user.setUserName(resultSet.getString("userName"));
               user.setPassword(resultSet.getString("password"));
               user.setFullName(resultSet.getString("fullName"));
               user.setEmail(resultSet.getString("email"));
               user.setAddress(resultSet.getString("address"));
               user.setPhone(resultSet.getString("phone"));
               user.setGender(EGender.valueOf(resultSet.getString("gender")));
               user.setDob(resultSet.getDate("dob"));
               user.setRole(roleDao.getRoleById(resultSet.getInt("role_id")));
               userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
    public User getUserById(int id){
        String SELECT_USER_BY_ID = "SELECT * FROM `user` WHERE id = ?";

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){{
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("fullName"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setPhone(resultSet.getString("phone"));
                user.setGender(EGender.valueOf(resultSet.getString("gender")));
                user.setDob(resultSet.getDate("dob"));
                user.setRole(roleDao.getRoleById(resultSet.getInt("role_id")));
                return user;
            }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

//    public static void main(String[] args) {
//        UserDao userDao = new UserDao();
//        System.out.println(userDao.getUserById(1).getDob());
//    }
}
