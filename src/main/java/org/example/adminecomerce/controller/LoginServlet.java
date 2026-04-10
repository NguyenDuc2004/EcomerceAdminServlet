package org.example.adminecomerce.controller;

import org.example.adminecomerce.model.User;
import org.example.adminecomerce.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("logout".equals(action)) {
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            HttpSession newSession = req.getSession();
            newSession.setAttribute("toastMsg", "Đăng xuất thành công!");
            newSession.setAttribute("toastType", "success");

            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.getRequestDispatcher("page/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User account = userService.checkLogin(username, password);
        HttpSession session = req.getSession();

        if (account != null) {
            if (account.getStatus() == 0) {
                req.setAttribute("toastMsg", "Tài khoản của bạn đã bị khóa!");
                req.setAttribute("toastType", "danger");
                req.getRequestDispatcher("page/login.jsp").forward(req, resp);
                return;
            }

            session.setAttribute("loginedUser", account);
            session.setAttribute("toastMsg", "Chào mừng " + account.getFullname() + " quay trở lại!");
            session.setAttribute("toastType", "success");

            if (account.isAdmin()) {
                resp.sendRedirect(req.getContextPath() + "/dashboard");
            } else {
                resp.sendRedirect(req.getContextPath() + "/index.jsp"); // Hoặc trang chủ của khách
            }
        } else {
            req.setAttribute("toastMsg", "Tài khoản hoặc mật khẩu không chính xác!");
            req.setAttribute("toastType", "danger");
            req.getRequestDispatcher("page/login.jsp").forward(req, resp);
        }
    }
}