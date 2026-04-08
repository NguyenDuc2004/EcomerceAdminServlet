<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">

  <style>
    body { background-color: #f4f7f6; }
    .sidebar { min-height: 100vh; background: #2c3e50; color: white; }
    .nav-link { color: rgba(255,255,255,0.7); transition: 0.3s; }
    .nav-link:hover, .nav-link.active { color: white; background: rgba(255,255,255,0.1); }
    .main-content { padding: 20px; }
    /* Style cho thông báo trượt ra */
    #success-alert {
      position: fixed;
      top: 20px;
      right: 20px;
      z-index: 10000;
      min-width: 300px;
      box-shadow: 0 4px 12px rgba(0,0,0,0.15);
    }
  </style>
</head>
<body>

<%
  String successMsg = (String) session.getAttribute("successMsg");
  if (successMsg != null) {
%>
<div id="success-alert" class="alert alert-success alert-dismissible fade show" role="alert">
  <i class="bi bi-check-circle-fill me-2"></i>
  <strong>Thành công!</strong> <%= successMsg %>
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>

<script>

  setTimeout(function() {
    var alertNode = document.getElementById('success-alert');
    if (alertNode) {
      var bsAlert = new bootstrap.Alert(alertNode);
      bsAlert.close();
    }
  }, 3000);
</script>
<%
    // Quan trọng: Hiển thị xong thì xóa ngay để F5 không hiện lại
    session.removeAttribute("successMsg");
  }
%>

<div class="container-fluid">
  <div class="row">
    <nav class="col-md-3 col-lg-2 d-md-block sidebar p-3">
      <h4 class="text-center mb-4">ADMIN</h4>
      <ul class="nav flex-column">
        <li class="nav-item mb-2">
          <a class="nav-link active rounded" href="#"><i class="bi bi-speedometer2 me-2"></i> Dashboard</a>
        </li>
        <li class="nav-item mb-2">
          <a class="nav-link rounded" href="user"><i class="bi bi-people me-2"></i> Quản lý User</a>
        </li>
        <li class="nav-item mb-2">
          <a class="nav-link rounded" href="#"><i class="bi bi-box-seam me-2"></i> Sản phẩm</a>
        </li>
        <hr>
        <li class="nav-item">
          <a class="nav-link rounded text-danger" href="${pageContext.request.contextPath}/login?action=logout">
            <i class="bi bi-box-arrow-right me-2"></i> Đăng xuất
          </a>
        </li>
      </ul>
    </nav>

    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <nav class="navbar navbar-expand-lg navbar-light bg-white mb-4 shadow-sm rounded mt-3">
        <div class="container-fluid">
          <span class="navbar-brand">Tổng quan hệ thống</span>
          <div class="d-flex align-items-center">
            <span class="me-3 text-muted">Xin chào, <strong>${loginedUser.fullname}</strong></span>
            <img src="https://ui-avatars.com/api/?name=${loginedUser.fullname}&background=random" class="rounded-circle" width="35">
          </div>
        </div>
      </nav>

      <div class="main-content bg-white rounded shadow-sm">
        <h3>Thống kê nhanh</h3>
        <div class="row mt-4">
          <div class="col-md-4">
            <div class="card bg-primary text-white p-3 mb-3">
              <h5>Người dùng</h5>
              <h2>1,250</h2>
            </div>
          </div>
          <div class="col-md-4">
            <div class="card bg-success text-white p-3 mb-3">
              <h5>Đơn hàng mới</h5>
              <h2>45</h2>
            </div>
          </div>
          <div class="col-md-4">
            <div class="card bg-warning text-dark p-3 mb-3">
              <h5>Doanh thu</h5>
              <h2>$12,400</h2>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>