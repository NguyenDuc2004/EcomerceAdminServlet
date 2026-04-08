<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hệ thống Quản trị | Đăng nhập</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">

    <style>
        body {
            font-family: 'Inter', sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0;
        }
        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.3);
            overflow: hidden;
        }
        .login-header {
            background: #fff;
            padding: 2rem 2rem 1rem;
            text-align: center;
        }
        .btn-primary {
            background-color: #764ba2;
            border: none;
            padding: 12px;
            font-weight: 600;
            transition: all 0.3s ease;
        }
        .btn-primary:hover {
            background-color: #5a3a8a;
            transform: translateY(-1px);
        }
        .form-control:focus {
            border-color: #764ba2;
            box-shadow: 0 0 0 0.25rem rgba(118, 75, 162, 0.25);
        }
        .input-group-text {
            background-color: #f8f9fa;
            color: #6c757d;
        }
        #togglePassword {
            cursor: pointer;
            transition: color 0.2s;
        }
        #togglePassword:hover {
            color: #764ba2;
        }
        .error-shake {
            animation: shake 0.5s;
        }
        @keyframes shake {
            0%, 100% {transform: translateX(0);}
            25% {transform: translateX(-5px);}
            75% {transform: translateX(5px);}
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-5 col-lg-4">
            <div class="card p-2">
                <div class="login-header">
                    <div class="display-6 fw-bold text-dark">Đăng nhập</div>

                </div>

                <div class="card-body">
                    <% if(request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger d-flex align-items-center mb-4 error-shake" role="alert">
                        <i class="bi bi-exclamation-triangle-fill me-2"></i>
                        <div style="font-size: 0.9rem;"><%= request.getAttribute("error") %></div>
                    </div>
                    <% } %>

                    <form action="login" method="post" id="loginForm" class="needs-validation" novalidate>
                        <div class="mb-3">
                            <label class="form-label fw-semibold text-secondary small">TÀI KHOẢN</label>
                            <div class="input-group has-validation">
                                <span class="input-group-text"><i class="bi bi-person"></i></span>
                                <input type="text" name="username" class="form-control"
                                       id="username" placeholder="Nhập tên đăng nhập"
                                       maxlength="20" required>
                                <div class="invalid-feedback">
                                    Tên đăng nhập không được để trống.
                                </div>
                            </div>
                            <div class="form-text mt-1" style="font-size: 0.75rem;">Tối đa 20 ký tự.</div>
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-semibold text-secondary small">MẬT KHẨU</label>
                            <div class="input-group has-validation">
                                <span class="input-group-text"><i class="bi bi-lock"></i></span>
                                <input type="password" name="password" class="form-control"
                                       id="password" placeholder="••••••••"
                                       maxlength="30" required>
                                <span class="input-group-text" id="togglePassword">
                                    <i class="bi bi-eye-slash" id="eyeIcon"></i>
                                </span>
                                <div class="invalid-feedback">
                                    Vui lòng nhập mật khẩu.
                                </div>
                            </div>
                        </div>

                        <button type="submit" class="btn btn-primary w-100 shadow-sm mb-3">
                            ĐĂNG NHẬP
                        </button>
                    </form>

                    <div class="text-center mt-2">
                        <a href="#" class="text-decoration-none small text-muted">Quên mật khẩu?</a>
                    </div>
                </div>
            </div>
            <p class="text-center text-white-50 mt-4 small">&copy; 2026 E-Comerce Duc Dep Trai</p>
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

        // Hoán đổi icon mắt
        eyeIcon.classList.toggle('bi-eye');
        eyeIcon.classList.toggle('bi-eye-slash');
    });

    // 2. Bootstrap Validation
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

    // 3. Giới hạn ký tự ngay khi gõ (Double protection)
    document.getElementById('username').addEventListener('input', function() {
        if (this.value.length > 20) this.value = this.value.slice(0, 20);
    });
</script>

</body>
</html>