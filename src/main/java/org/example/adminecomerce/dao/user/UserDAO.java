package org.example.adminecomerce.dao.user;

import org.example.adminecomerce.dbConnection.DbConnection;
import org.example.adminecomerce.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{
    public static final String CHECK_LOGIN = "SELECT u.id, u.username, u.fullname, u.email, r.role_name " +
            "FROM users u " +
            "JOIN roles r ON u.role_id = r.id " +
            "WHERE u.username = ? AND u.password = ? AND u.status = 1";

    public static final String COUNT_USER_BY_ROLENAME = "SELECT COUNT(*) FROM USERS WHERE role_id = (SELECT id from roles WHERE role_name = ?)";;
    public static final String SELECT_LIST_USERS = "SELECT u.id, u.username, u.email,roles.role_name, u.status, u.created_at ,u.phone,u.fullname,u.address FROM USERS as u join roles on u.role_id = roles.id;";
    public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE ID = ?;";
    public static final String SELECT_USER_BY_ID = "SELECT u.*, r.role_name " +
            "FROM users u " +
            "JOIN roles r ON u.role_id = r.id " +
            "WHERE u.id = ?";
    public static final String UPDATE_USER_BY_ID = "UPDATE users SET fullname = ?, email = ?, phone = ?, address = ?, role_id = ?, status = ? WHERE id = ?";
    public static final String SEARCH_FILTER_USER = "SELECT u.*, r.role_name FROM users u JOIN roles r ON u.role_id = r.id WHERE 1=1 ";
    public static final String INSERT_USER = "INSERT INTO users (username, password, fullname, email, role_id) VALUES (?, ?, ?, ?, ?);";

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
        List<User> users = new ArrayList<>();
        try(Connection conn = DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(SELECT_LIST_USERS)
        ) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFullname(rs.getString("fullname"));
                u.setEmail(rs.getString("email"));
                u.setRoleName(rs.getString("role_name"));
                u.setStatus(rs.getInt("status"));
                u.setCreatedAt(rs.getString("created_at"));
                u.setPhone(rs.getString("phone"));
                u.setAddress(rs.getString("address"));

                users.add(u);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(int id) {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFullname(rs.getString("fullname"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setAddress(rs.getString("address"));
                u.setRoleName(rs.getString("role_name"));
                u.setStatus(rs.getInt("status"));
                u.setCreatedAt(rs.getString("created_at"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean insertUser(User user) {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_USER)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullname());
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getRoleId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        String sql = UPDATE_USER_BY_ID;
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getAddress());
            ps.setInt(5, user.getRoleId());
            ps.setInt(6, user.getStatus());
            ps.setInt(7, user.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_USER_BY_ID)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa user ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean isUsernameExists(String username) {
        return false;
    }

    @Override
    public int countUsersByRole(String roleName) {
        try(Connection conn = DbConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(COUNT_USER_BY_ROLENAME);
        ) {
            if (roleName != null) preparedStatement.setString(1, roleName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<User> filterUsers(String keyword, String roleName, String status) {
        List<User> users = new ArrayList<>();
        StringBuilder sql = new StringBuilder(SEARCH_FILTER_USER);
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND (u.username LIKE ? OR u.fullname LIKE ? OR u.email LIKE ?) ");
        }
        if (roleName != null && !roleName.isEmpty()) {
            sql.append(" AND r.role_name = ? ");
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" AND u.status = ? ");
        }
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int paramIndex = 1;
            if (keyword != null && !keyword.isEmpty()) {
                String pattern = "%" + keyword + "%";
                ps.setString(paramIndex++, pattern);
                ps.setString(paramIndex++, pattern);
                ps.setString(paramIndex++, pattern);
            }
            if (roleName != null && !roleName.isEmpty()) {
                ps.setString(paramIndex++, roleName);
            }
            if (status != null && !status.isEmpty()) {
                ps.setInt(paramIndex++, Integer.parseInt(status));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int roleId = rs.getInt("role_id");
                String createAt = rs.getString("created_at");
                int uStatus = rs.getInt("status");
                String rName = rs.getString("role_name");
                User user = new User(id, username, fullname, email, phone, address, rName,createAt, uStatus);
                users.add(user);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return users;
    }
}
