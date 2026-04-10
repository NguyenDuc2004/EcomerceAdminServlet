package org.example.adminecomerce.controller;


import org.example.adminecomerce.model.User;
import org.example.adminecomerce.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet" ,value = "/user")
public class UserServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("loginedUser");
        String role = (currentUser != null) ? currentUser.getRoleName() : "";

        if (!"ADMIN".equals(role)) {
            HttpSession session = req.getSession();
            session.setAttribute("toastMsg", "Bạn không có quyền truy cập vào khu vực này!");
            session.setAttribute("toastType", "danger");
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }
        String action = req.getParameter("action");
        if(action == null) action = "";
        switch (action){
            case "view":
                detailUser(req,resp);
                break;
            case "edit":
                showFormEdit(req,resp);
                break;
            case "delete":
                deletedUser(req,resp);
                break;
            default:
                getListUsers(req, resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null) action = "";
        switch (action) {
            case "edit":
                updateUser(req,resp);
                break;
            case "create":
                addUser(req,resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/user");
                break;
        }
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        User newUser = new User(username,password,fullname,email,roleId);
        boolean isAdded = userService.createUser(newUser);

        HttpSession session = req.getSession();
        if (isAdded) {
            session.setAttribute("toastMsg", "Đã thêm thành công tài khoản: " + username);
            session.setAttribute("toastType", "success");
        } else {
            session.setAttribute("toastMsg", "Lỗi! Tên đăng nhập hoặc Email đã tồn tại.");
            session.setAttribute("toastType", "danger");
        }

        resp.sendRedirect(req.getContextPath() + "/user");
    }
    private void getListUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        String roleName = req.getParameter("role");
        String status = req.getParameter("status");

        List<User> users = userService.filterSearchUser(keyword, roleName, status);
        req.setAttribute("listUser", users);
        req.setAttribute("keyword", keyword);
        req.setAttribute("selectedRole", roleName);
        req.setAttribute("selectedStatus", status);


        req.setAttribute("view", "users");
        req.getRequestDispatcher("admin/layout.jsp").forward(req, resp);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");
            int roleId = Integer.parseInt(req.getParameter("roleId"));
            String statusParam = req.getParameter("status");
            int status = (statusParam != null && statusParam.equals("1")) ? 1 : 0;

            User user = new User(id, fullname, email, phone, address, roleId, status);
            boolean isUpdated = userService.updateUser(user);
            HttpSession session = req.getSession();
            if (isUpdated) {
                User loginedUser = (User) session.getAttribute("loginedUser");
                if (loginedUser != null && loginedUser.getId() == id) {
                    if (status == 0 || roleId != 1){
                        session.invalidate();
                        resp.sendRedirect(req.getContextPath() + "/login");
                        return;
                    }
                    session.setAttribute("loginedUser", userService.getDetailUser(id));
                }

                session.setAttribute("toastMsg", "Cập nhật thành công tài khoản: " + fullname);
                session.setAttribute("toastType", "success");
            } else {
                session.setAttribute("toastMsg", "Cập nhật thất bại!");
                session.setAttribute("toastType", "danger");
            }

            resp.sendRedirect(req.getContextPath() + "/user");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/user?message=error");
        }
    }


//    private void getListUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<User> users = new ArrayList<>();
//        users = userService.getAllUsers();
//        req.setAttribute("view", "users");
//        req.setAttribute("listUser",users);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("admin/layout.jsp");
//        dispatcher.forward(req, resp);
//    }

    private void deletedUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("id");
        HttpSession session = req.getSession(); // Lấy session để nạp Toast

        // 1. Kiểm tra ID đầu vào
        if (idStr == null || idStr.isEmpty()) {
            session.setAttribute("toastMsg", "ID người dùng không hợp lệ!");
            session.setAttribute("toastType", "danger");
            resp.sendRedirect(req.getContextPath() + "/user");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            boolean isDeleted = userService.deleteUserById(id);

            if (isDeleted) {
                session.setAttribute("toastMsg", "Đã xóa thành công tài khoản ID: " + id);
                session.setAttribute("toastType", "success");
            } else {
                session.setAttribute("toastMsg", "Không thể xóa! Người dùng này có thể đang có dữ liệu liên quan (hóa đơn, bài viết...).");
                session.setAttribute("toastType", "danger");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            session.setAttribute("toastMsg", "Lỗi định dạng ID!");
            session.setAttribute("toastType", "danger");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("toastMsg", "Đã xảy ra lỗi hệ thống khi xóa!");
            session.setAttribute("toastType", "danger");
        }
        resp.sendRedirect(req.getContextPath() + "/user");
    }

    private void detailUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userService.getDetailUser(id);
        req.setAttribute("user",user);
        req.setAttribute("view","view");
        req.getRequestDispatcher("admin/layout.jsp").forward(req,resp);
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            User existingUser = userService.getDetailUser(id);

            if (existingUser != null) {
                req.setAttribute("user", existingUser);

                req.setAttribute("view", "edit");

                req.getRequestDispatcher("/admin/layout.jsp").forward(req, resp);
            }
//            else {
//
//                resp.sendRedirect("user?message=not_found");
//            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("user?message=error");
        }
    }
}
