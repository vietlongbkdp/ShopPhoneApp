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
        String SELECT_ALL_USER = "SELECT * FROM `users`";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(SELECT_ALL_USER);
            while (resultSet.next()){
               User user = new User();
               user.setId(resultSet.getInt("id"));
               user.setUserName(resultSet.getString("userName"));
               user.setPassword(resultSet.getString("password"));
               user.setFullName(resultSet.getString("fullName"));
               user.setEmail(resultSet.getString("email"));
                if(resultSet.getString("address")!=null){
                    user.setAddress(resultSet.getString("address"));
                }else user.setAddress(null);
                if(resultSet.getString("phone")!=null){
                    user.setPhone(resultSet.getString("phone"));
                }else user.setPhone(null);
                if(resultSet.getDate("dob")!=null){
                    user.setDob(resultSet.getDate("dob"));
                }else user.setDob(null);
                if(resultSet.getString("gender")!=null){
                    user.setGender(EGender.valueOf(resultSet.getString("gender")));
                }else user.setGender(null);
               user.setRole(roleDao.getRoleById(resultSet.getInt("role_id")));
               userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch (NullPointerException ignored){
        }
        return userList;
    }
    public User getUserById(int id){
        String SELECT_USER_BY_ID = "SELECT * FROM `users` WHERE id = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setPassword(resultSet.getString("password"));
                    user.setFullName(resultSet.getString("fullName"));
                    user.setEmail(resultSet.getString("email"));
                    if(resultSet.getString("address")!=null){
                        user.setAddress(resultSet.getString("address"));
                    }else user.setAddress(null);
                    if(resultSet.getString("phone")!=null){
                    user.setPhone(resultSet.getString("phone"));
                    }else user.setPhone(null);
                    if(resultSet.getDate("dob")!=null){
                    user.setDob(resultSet.getDate("dob"));
                    }else user.setDob(null);
                    if(resultSet.getString("gender")!=null){
                    user.setGender(EGender.valueOf(resultSet.getString("gender")));
                    }else user.setGender(null);
                    user.setRole(roleDao.getRoleById(resultSet.getInt("role_id")));
                    return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void register(String fullName, String userName, String email, String password) {
        String REGISTER_CLIENT = "INSERT INTO `users` (`username`, `password`, `fullname`, `email`, `role_id`) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_CLIENT);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,fullName);
            preparedStatement.setString(4,email);
            preparedStatement.setInt(5,roleDao.getRoleByRoleName("Client").getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteUserById(int id){
        String DELETE_USER_BY_ID = "DELETE FROM `users` WHERE (`id` = ?)";

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createUser(User user){
        String CREATE_NEW_USER = "INSERT INTO `datacasemd3`.`users` (`username`, `password`, `fullname`, `email`, `address`, `phone`, `gender`, `dob`, `role_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEW_USER);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getGender().name());
            preparedStatement.setDate(8, user.getDob());
            preparedStatement.setInt(9, user.getRole().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editUser(User user, int id) {
        String EDIT_USER_BY_ID = "UPDATE `users` SET `username` = ?, `password` = ?, `fullname` = ?, `email` = ?, `address` = ?, `phone` = ?, `gender` = ?, `dob` = ?, `role_id` = ? WHERE (`id` = ?)";

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_USER_BY_ID);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getGender().name());
            preparedStatement.setDate(8, user.getDob());
            preparedStatement.setInt(9, user.getRole().getId());
            preparedStatement.setInt(10, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
//        UserDao userDao = new UserDao();
//        System.out.println(userDao.getUserById(7).getUserName());
//    }
}
