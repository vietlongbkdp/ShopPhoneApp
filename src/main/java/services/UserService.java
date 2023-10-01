package services;

import daos.RoleDao;
import daos.UserDao;
import models.User;

import java.util.List;

public class UserService {
    RoleDao roleDao = new RoleDao();
    UserDao userDao = new UserDao();
    public List<User> getAllUser(){
        return userDao.getAllUser();
    }
    public User getUserById(int id){
        return userDao.getUserById(id);
    }
    public User getUserByUserName(String userName){
        List<User> userList = getAllUser();
        return userList.stream().filter(user -> user.getUserName().equalsIgnoreCase(userName)).findFirst().orElse(null);
    }

    public boolean login(String userName, String password) {
        List<User> userList = getAllUser();
        for (User u:userList) {
            if(u.getUserName().equalsIgnoreCase(userName) && u.getPassword().equals(password)) return true;
        }
        return false;
    }
}
