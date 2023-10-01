package services;

import daos.RoleDao;
import daos.UserDao;
import models.Role;

import java.util.List;

public class RoleService {
    RoleDao roleDao = new RoleDao();
    UserDao userDao = new UserDao();
    public Role getRoleById(int id){
        return roleDao.getRoleById(id);
    }
    public List<Role> getAllRole(){
        return roleDao.getAllRole();
    }
}
