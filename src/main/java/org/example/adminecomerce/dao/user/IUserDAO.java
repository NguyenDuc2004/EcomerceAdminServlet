package org.example.adminecomerce.dao.user;

import org.example.adminecomerce.model.User;

import java.util.List;

public interface IUserDAO {
    User checkLogin(String username, String password);
    List<User> getAllUsers();
    User getUserById(int id);
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    //  Kiểm tra trùng lặp username khi đăng ký/thêm mới
    boolean isUsernameExists(String username);
}
