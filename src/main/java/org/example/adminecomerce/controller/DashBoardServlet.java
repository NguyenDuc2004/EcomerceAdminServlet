package org.example.adminecomerce.controller;

import org.example.adminecomerce.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DashBoardServlet", value = "/dashboard")

public class DashBoardServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("loginedUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        // thong ke tong so nguoi dung
        int count = userService.CountUserByRoleName("USER");
        req.setAttribute("countUser",count);
        req.setAttribute("view","dashboard");
        req.getRequestDispatcher("/admin/layout.jsp").forward(req, resp);
    }
}
