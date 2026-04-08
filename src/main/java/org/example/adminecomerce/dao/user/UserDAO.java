package org.example.adminecomerce.dao.user;

import org.example.adminecomerce.dbConnection.DbConnection;
import org.example.adminecomerce.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements IUserDAO{
    public static final String CHECK_LOGIN = "SELECT u.id, u.username, u.fullname, u.email, r.role_name " +
            "FROM users u " +
            "JOIN roles r ON u.role_id = r.id " +
            "WHERE u.username = ? AND u.password = ? AND u.status = 1";


    @Override
    public User checkLogin(String username, String password) {
        try(Connection conn = DbConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(CHECK_LOGIN);
        ) {
            preparedStatement.setString(1,username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("role_name"),
                        1
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public boolean insertUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public boolean isUsernameExists(String username) {
        return false;
    }
}
