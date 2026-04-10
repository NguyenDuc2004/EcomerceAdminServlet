<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hệ thống Quản trị | Đăng nhập</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">

    <style>
        :root {
            --primary-color: #007bff;
            --primary-hover: #0056b3;
            --bg-gradient: linear-gradient(135deg, #e0eafc 0%, #cfdef3 100%);
        }

        body, html {
            height: 100%;
            margin: 0;
            font-family: 'Plus Jakarta Sans', sans-serif;
            background: var(--bg-gradient);
            overflow: hidden;
        }

        .login-container {
            display: flex;
            height: 100vh;
            width: 100vw;
        }

        /* PHẦN BÊN TRÁI: FORM ĐĂNG NHẬP */
        .login-side {
            flex: 0 0 500px; /* Độ rộng vùng login */
            background: rgba(255, 255, 255, 0.6);
            backdrop-filter: blur(20px);
            -webkit-backdrop-filter: blur(20px);
            display: flex;
            align-items: center;
            padding: 0 4rem;
            border-right: 1px solid rgba(255, 255, 255, 0.3);
            z-index: 10;
        }

        /* PHẦN BÊN PHẢI: BANNER FULL WIDTH */
        .banner-side {
            flex: 1;
            background: linear-gradient(135deg, rgba(0, 123, 255, 0.9) 0%, rgba(0, 64, 133, 0.9) 100%),
            url('https://images.unsplash.com/photo-1460925895917-afdab827c52f?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80');
            background-size: cover;
            background-position: center;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
        }

        /* Các đốm sáng trang trí mờ ảo */
        .glow-circle {
            position: absolute;
            width: 400px;
            height: 400px;
            background: rgba(0, 123, 255, 0.15);
            filter: blur(80px);
            border-radius: 50%;
            z-index: 1;
        }

        .login-form-wrapper {
            width: 100%;
            position: relative;
            z-index: 2;
        }

        .form-control {
            border-radius: 12px;
            padding: 0.8rem 1rem;
            border: 1px solid rgba(0, 0, 0, 0.08);
            background: rgba(255, 255, 255, 0.5);
            transition: all 0.3s ease;
        }

        .form-control:focus {
            background: #fff;
            box-shadow: 0 0 0 4px rgba(0, 123, 255, 0.1);
            border-color: var(--primary-color);
        }

        .input-group-text {
            border-radius: 12px;
            background: rgba(255, 255, 255, 0.5);
            border: 1px solid rgba(0, 0, 0, 0.08);
            color: var(--primary-color);
        }

        .btn-primary {
            background: var(--primary-color);
            border: none;
            border-radius: 12px;
            padding: 14px;
            font-weight: 700;
            letter-spacing: 0.5px;
            box-shadow: 0 8px 20px rgba(0, 123, 255, 0.3);
            transition: all 0.3s ease;
        }

        .btn-primary:hover {
            background: var(--primary-hover);
            transform: translateY(-2px);
            box-shadow: 0 12px 25px rgba(0, 123, 255, 0.4);
        }

        .banner-content {
            color: white;
            text-align: center;
            max-width: 600px;
            padding: 2rem;
            backdrop-filter: blur(5px);
            background: rgba(255, 255, 255, 0.05);
            border-radius: 24px;
            border: 1px solid rgba(255, 255, 255, 0.1);
        }

        /* Animation cho thông báo lỗi */
        .error-shake {
            animation: shake 0.5s cubic-bezier(.36,.07,.19,.97) both;
        }

        @keyframes shake {
            10%, 90% { transform: translate3d(-1px, 0, 0); }
            20%, 80% { transform: translate3d(2px, 0, 0); }
            30%, 50%, 70% { transform: translate3d(-3px, 0, 0); }
            40%, 60% { transform: translate3d(3px, 0, 0); }
        }

        @media (max-width: 850px) {
            .banner-side { display: none; }
            .login-side { flex: 1; padding: 0 2rem; }
        }
    </style>
</head>
<body>

<div class="login-container">
    <div class="glow-circle" style="top: -100px; left: -100px;"></div>

    <div class="login-side">
        <div class="login-form-wrapper">
            <div class="mb-5">
                <h2 class="fw-bold text-dark display-6 mb-2">Đăng nhập</h2>
                <p class="text-secondary">Chào mừng quay trở lại</p>
            </div>

            <c:if test="${not empty toastMsg}">
                <div class="alert alert-${toastType == 'success' ? 'success' : 'danger'} d-flex align-items-center mb-4 error-shake"
                     style="border-radius: 12px; background: ${toastType == 'success' ? 'rgba(40,167,69,0.1)' : 'rgba(220,53,69,0.1)'};
                             color: ${toastType == 'success' ? '#155724' : '#721c24'}; border: none;" role="alert">
                    <i class="bi ${toastType == 'success' ? 'bi-check-circle' : 'bi-exclamation-circle'} me-2"></i>
                    <div class="small">${toastMsg}</div>
                </div>
                <c:remove var="toastMsg" scope="session"/>
                <c:remove var="toastType" scope="session"/>
            </c:if>

            <form action="login" method="post" class="needs-validation" novalidate>
                <div class="mb-4">
                    <label class="form-label fw-bold small text-muted">TÀI KHOẢN</label>
                    <div class="input-group">
                        <span class="input-group-text border-end-0"><i class="bi bi-person-circle"></i></span>
                        <input type="text" name="username" class="form-control border-start-0"
                               placeholder="Nhập tên đăng nhập" required>
                    </div>
                </div>

                <div class="mb-4">
                    <label class="form-label fw-bold small text-muted">MẬT KHẨU</label>
                    <div class="input-group">
                        <span class="input-group-text border-end-0"><i class="bi bi-shield-lock"></i></span>
                        <input type="password" name="password" id="password" class="form-control border-start-0 border-end-0"
                               placeholder="••••••••" required>
                        <span class="input-group-text border-start-0" id="togglePassword" style="cursor: pointer;">
                            <i class="bi bi-eye-slash" id="eyeIcon"></i>
                        </span>
                    </div>
                </div>

                <div class="d-flex justify-content-between align-items-center mb-5">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="rememberMe">
                        <label class="form-check-label small text-muted" for="rememberMe">Ghi nhớ tôi</label>
                    </div>
                    <a href="#" class="text-decoration-none small fw-bold" style="color: var(--primary-color);">Quên mật khẩu?</a>
                </div>

                <button type="submit" class="btn btn-primary w-100 shadow">
                    Đăng nhập <i class="bi bi-arrow-right-short ms-1"></i>
                </button>
            </form>

            <div class="mt-5 text-center">
                <p class="text-muted small">&copy; 2026 Powered by Duc. All rights reserved.</p>
            </div>
        </div>
    </div>

    <div class="banner-side">
        <div class="banner-content shadow-lg">
            <h1 class="display-4 fw-bold mb-4">E-Commerce</h1>
            <p class="lead mb-0 opacity-75">
                DẠO NÀY EM THẾ NÀO ? CÓ HAY THỨC KHUYA........
            </p>
        </div>

        <div style="position: absolute; bottom: 5%; right: 5%; color: rgba(255,255,255,0.2); font-size: 5rem;">
            <i class="bi bi-cpu"></i>
        </div>
    </div>
</div>

<script>
    const togglePassword = document.querySelector('#togglePassword');
    const passwordInput = document.querySelector('#password');
    const eyeIcon = document.querySelector('#eyeIcon');

    togglePassword.addEventListener('click', function () {
        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordInput.setAttribute('type', type);
        eyeIcon.classList.toggle('bi-eye');
        eyeIcon.classList.toggle('bi-eye-slash');
    });

    (function () {
        'use strict'
        const forms = document.querySelectorAll('.needs-validation')
        Array.prototype.slice.call(forms).forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }
                form.classList.add('was-validated')
            }, false)
        })
    })()
</script>

</body>
</html>