<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- Khai báo thư viện JSTL Core --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ - My Shop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">DUC-ECOMMERCE</a>

        <div class="ms-auto">
            <c:choose>
                <%-- TRƯỜNG HỢP 1: CHƯA ĐĂNG NHẬP --%>
                <c:when test="${empty sessionScope.loginedUser}">
                    <a href="login" class="btn btn-outline-light me-2">Đăng nhập</a>
                    <a href="#" class="btn btn-warning">Đăng ký</a>
                </c:when>

                <%-- TRƯỜNG HỢP 2: ĐÃ ĐĂNG NHẬP --%>
                <c:otherwise>
                    <div class="dropdown d-inline">
                        <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown">
                            Chào, ${sessionScope.loginedUser.fullname}
                        </button>
                        <ul class="dropdown-menu">
                                <%-- Kiểm tra nếu là ADMIN thì hiện thêm nút Dashboard --%>
                            <c:if test="${sessionScope.loginedUser.admin}">
                                <li><a class="dropdown-item text-danger fw-bold" href="dashboard">Quản trị Hệ thống</a></li>
                                <li><hr class="dropdown-divider"></li>
                            </c:if>

                            <li><a class="dropdown-item" href="#">Thông tin cá nhân</a></li>
                            <li><a class="dropdown-item" href="login?action=logout">Đăng xuất</a></li>
                        </ul>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="p-5 mb-4 bg-light rounded-3">
        <div class="container-fluid py-5">
            <h1 class="display-5 fw-bold">Trang web khách hàng</h1>
            <p class="col-md-8 fs-4">
                Tại đây khách hàng có thể xem sản phẩm, mua hàng và quản lý giỏ hàng của mình.
            </p>
            <c:if test="${not empty sessionScope.loginedUser}">
                <div class="alert alert-info">
                    Bạn đang đăng nhập với vai trò:
                    <strong>${sessionScope.loginedUser.admin ? 'Quản trị viên' : 'Khách hàng'}</strong>
                </div>
            </c:if>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>