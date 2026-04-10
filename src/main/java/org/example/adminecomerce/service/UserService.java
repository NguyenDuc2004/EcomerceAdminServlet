package org.example.adminecomerce.service;

import org.example.adminecomerce.dao.user.IUserDAO;
import org.example.adminecomerce.dao.user.UserDAO;
import org.example.adminecomerce.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    IUserDAO userDAO = new UserDAO();

    public User checkLogin(String username, String password){
        return userDAO.checkLogin(username,password);
    }
    public int CountUserByRoleName(String roleName){
        return userDAO.countUsersByRole(roleName);
    }
    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }
    public boolean deleteUserById(int id) throws SQLException {
        return userDAO.deleteUser(id);
    }
    public User getDetailUser(int id){
        return userDAO.getUserById(id);
    }

    public boolean updateUser(User user){
        return userDAO.updateUser(user);
    }

    public List<User> filterSearchUser(String keyword, String roleName, String status) {
        if (keyword != null) {
            keyword = keyword.trim();
        }
        if ("".equals(roleName) || "all".equalsIgnoreCase(roleName)) {
            roleName = null;
        }
        if ("".equals(status) || "all".equalsIgnoreCase(status)) {
            status = null;
        }
        return userDAO.filterUsers(keyword, roleName, status);
    }
    public boolean createUser(User user){
        return userDAO.insertUser(user);
    }

}
