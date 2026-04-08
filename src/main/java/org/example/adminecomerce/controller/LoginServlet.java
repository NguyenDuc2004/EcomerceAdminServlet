package org.example.adminecomerce.controller;


import org.example.adminecomerce.model.User;
import org.example.adminecomerce.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("logout".equals(action)) {
            HttpSession session = req.getSession();
            session.invalidate(); // Xóa sạch session
            resp.sendRedirect("page/login.jsp");
            return;
        }

        req.getRequestDispatcher("page/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User account = userService.checkLogin(username, password);
        if (account != null) {
          //lg thanh cong thi luu vao session
            HttpSession session = req.getSession();
            session.setAttribute("loginedUser", account);

            session.setAttribute("successMsg", "Đăng nhập thành công! Chào mừng " + account.getFullname());
            //phan quyen
            if (account.isAdmin()) {
                resp.sendRedirect("admin/dashboard.jsp");
            } else {
                resp.sendRedirect("index.jsp");
            }
        } else {
            //that bai thi ve lai trang login
            req.setAttribute("error", "Tài khoản hoặc mật khẩu không chính xác!");
            req.getRequestDispatcher("page/login.jsp").forward(req,resp);
        }
    }
}
