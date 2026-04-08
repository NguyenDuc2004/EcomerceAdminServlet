package org.example.adminecomerce.service;

import org.example.adminecomerce.dao.user.IUserDAO;
import org.example.adminecomerce.dao.user.UserDAO;
import org.example.adminecomerce.model.User;

public class UserService {
    IUserDAO userDAO = new UserDAO();

    public User checkLogin(String username, String password){
        return userDAO.checkLogin(username,password);
    }
}
