<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="isUser" value="${user.roleName == 'USER'}" />

<style>
    /* Reset và làm đẹp khung */
    .edit-card { border-radius: 12px; border: none; box-shadow: 0 5px 20px rgba(0,0,0,0.1); overflow: hidden; }
    .header-dark { background: #1a1d20; color: white; padding: 20px; }
    .sys-panel { background: #f9f9f9; border-radius: 10px; padding: 20px; border: 1px solid #eee; height: 100%; }

    /* VẼ LẠI NÚT GẠT (CUSTOM SWITCH) - Đảm bảo hiện 100% */
    .status-toggle-box {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background: white;
        padding: 12px 15px;
        border: 1px solid #ddd;
        border-radius: 8px;
        margin-top: 8px;
    }
    .t-switch {
        position: relative;
        display: inline-block;
        width: 50px;
        height: 24px;
    }
    .t-switch input { opacity: 0; width: 0; height: 0; }
    .t-slider {
        position: absolute;
        cursor: pointer;
        top: 0; left: 0; right: 0; bottom: 0;
        background-color: #ccc;
        transition: .4s;
        border-radius: 24px;
    }
    .t-slider:before {
        position: absolute;
        content: "";
        height: 18px; width: 18px;
        left: 3px; bottom: 3px;
        background-color: white;
        transition: .4s;
        border-radius: 50%;
    }
    input:checked + .t-slider { background-color: #28a745; }
    input:checked + .t-slider:before { transform: translateX(26px); }
    input:disabled + .t-slider { background-color: #e9ecef; cursor: not-allowed; }

    /* Chặn Autofill của trình duyệt để không bị chèn chữ đè lên Select */
    input:-webkit-autofill { -webkit-box-shadow: 0 0 0 1000px white inset !important; }
</style>

<div class="container-fluid">
    <div class="mb-3">
        <a href="user" class="text-decoration-none text-muted small"><i class="bi bi-arrow-left"></i> Quay lại danh sách</a>
    </div>

    <div class="card edit-card">
        <div class="header-dark d-flex justify-content-between align-items-center">
            <h5 class="mb-0 fw-bold"><i class="bi bi-person-gear me-2 text-warning"></i>Cấu hình tài khoản: ${user.username}</h5>
            <span class="badge ${isUser ? 'bg-secondary' : 'bg-primary'}">${isUser ? 'CHẾ ĐỘ XEM' : 'ADMIN'}</span>
        </div>

        <form action="user?action=edit" method="POST" class="card-body p-4" autocomplete="off">
            <input type="text" style="display:none"><input type="password" style="display:none">

            <input type="hidden" name="id" value="${user.id}">

            <div class="row g-4">
                <div class="col-md-8 border-end">
                    <h6 class="fw-bold text-muted small text-uppercase mb-4">Thông tin cơ bản</h6>

                    <div class="mb-3">
                        <label class="form-label small fw-bold">Họ và tên</label>
                        <input type="text" name="fullname" class="form-control" value="${user.fullname}" ${isUser ? 'readonly' : ''} required>
                    </div>

                    <div class="row g-3">
                        <div class="col-md-6">
                            <label class="form-label small fw-bold">Email</label>
                            <input type="email" name="email" class="form-control" value="${user.email}" ${isUser ? 'readonly' : ''} required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label small fw-bold">Số điện thoại</label>
                            <input type="text" name="phone" class="form-control" value="${user.phone}" ${isUser ? 'readonly' : ''}>
                        </div>
                    </div>

                    <div class="mt-3">
                        <label class="form-label small fw-bold">Địa chỉ</label>
                        <textarea name="address" class="form-control" rows="3" ${isUser ? 'readonly' : ''}>${user.address}</textarea>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="sys-panel">
                        <h6 class="fw-bold text-muted small text-uppercase mb-4 text-center">Hệ thống</h6>

                        <div class="mb-4">
                            <label class="form-label small fw-bold">Nhóm quyền</label>
                            <select name="roleId" class="form-select border-2" ${isUser ? 'disabled' : ''}>
                                <option value="1" ${user.roleName == 'ADMIN' ? 'selected' : ''}>Quản trị viên</option>
                                <option value="2" ${user.roleName == 'STAFF' ? 'selected' : ''}>Nhân viên</option>
                                <option value="3" ${user.roleName == 'USER' ? 'selected' : ''}>Khách hàng</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label small fw-bold text-danger">Trạng thái hoạt động</label>
                            <div class="status-toggle-box">
                                <span class="fw-bold small text-secondary">Kích hoạt tài khoản</span>
                                <label class="t-switch">
                                    <input type="checkbox" name="status" value="1" ${user.status == 1 ? 'checked' : ''} ${isUser ? 'disabled' : ''}>
                                    <span class="t-slider"></span>
                                </label>
                            </div>
                        </div>

                        <div class="alert alert-light border-0 small mt-5">
                            <i class="bi bi-info-circle me-1"></i> Gạt nút để đóng/mở quyền truy cập của người dùng này.
                        </div>
                    </div>
                </div>
            </div>

            <div class="mt-5 pt-3 border-top d-flex justify-content-end gap-2">
                <a href="user" class="btn btn-light px-4 border">Thoát</a>
                <c:if test="${!isUser}">
                    <button type="submit" class="btn btn-primary px-5 fw-bold shadow">LƯU CẬP NHẬT</button>
                </c:if>
            </div>
        </form>
    </div>
</div>