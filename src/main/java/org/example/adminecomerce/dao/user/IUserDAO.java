package org.example.adminecomerce.dao.user;

import org.example.adminecomerce.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    User checkLogin(String username, String password);
    List<User> getAllUsers();
    User getUserById(int id);
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id) throws SQLException;
    //  Kiểm tra trùng lặp username khi đăng ký/thêm mới
    boolean isUsernameExists(String username);
    public int countUsersByRole(String roleName);
    List<User> filterUsers(String keyword, String roleName, String status);
}
