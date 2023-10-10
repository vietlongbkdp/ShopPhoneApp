package services;

import utils.PasswordUltis;
import daos.RoleDao;
import daos.UserDao;
import models.EGender;
import models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

public class UserService {
    RoleDao roleDao = new RoleDao();
    UserDao userDao = new UserDao();

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public User getUserByUserName(String userName) {
        List<User> userList = getAllUser();
        return userList.stream().filter(user -> user.getUserName().equalsIgnoreCase(userName)).findFirst().orElse(null);
    }

    public User getUserByEmail(String email) {
        List<User> userList = getAllUser();
        return userList.stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
    }

    public boolean login(String userName, String password) {
        List<User> userList = getAllUser();
        for (User u : userList) {
            if (u.getUserName().equalsIgnoreCase(userName) && PasswordUltis.checkPassword(password, u.getPassword())) return true;
        }
        return false;
    }

    public void register(String fullName, String userName, String email, String password) {
        userDao.register(fullName, userName, email, password);
    }

    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

    public void createUser(HttpServletRequest req) {
        User user = new User();
        user.setUserName(req.getParameter("userName"));
        user.setFullName(req.getParameter("fullName"));
        user.setEmail(req.getParameter("email"));
        user.setAddress(req.getParameter("address"));
        user.setPhone(req.getParameter("phone"));
        user.setDob(Date.valueOf(req.getParameter("dob")));
        user.setGender(EGender.valueOf(req.getParameter("gender")));
        user.setRole(roleDao.getRoleByRoleName(req.getParameter("role")));
        user.setPassword(PasswordUltis.encryptPassword(req.getParameter("password")));
        userDao.createUser(user);
    }

    public void edit(HttpServletRequest req) {
        User user = new User();
        int id = Integer.parseInt(req.getParameter("id"));
        user.setUserName(req.getParameter("userName"));
        user.setFullName(req.getParameter("fullName"));
        user.setEmail(req.getParameter("email"));
        user.setAddress(req.getParameter("address"));
        user.setPhone(req.getParameter("phone"));
        user.setDob(Date.valueOf(req.getParameter("dob")));
        user.setGender(EGender.valueOf(req.getParameter("gender")));
        user.setRole(roleDao.getRoleByRoleName(req.getParameter("role")));
        user.setPassword(PasswordUltis.encryptPassword(req.getParameter("password")));
        userDao.editUser(user, id);
    }

    public void updateProfile(HttpServletRequest req) {
        String userName = req.getParameter("userName");
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String gender = req.getParameter("gender");
        String dob = req.getParameter("dob");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        userDao.updateProfile(userName, fullName, email, address, phone, gender, dob, user.getId());
    }

    public void updatePassword(int id, String password) {
        userDao.updatePassword(id, PasswordUltis.encryptPassword(password));
    }

    public String getEmailById(int id) {
        User user = userDao.getAllUser().stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        if (user != null) {
            return user.getEmail();
        } else return null;
    }

    public boolean checkProfileUser(int id) {
        var check = true;
        User user = userDao.getUserById(id);
        if (user.getAddress().isEmpty() || user.getPhone().isEmpty()) {
            check = false;
        }
        return check;
    }
}
