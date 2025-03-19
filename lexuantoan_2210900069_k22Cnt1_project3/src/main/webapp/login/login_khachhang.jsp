<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập Khách hàng</title>
    <link rel="stylesheet" href="../assets/css/login_khachhang.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>

    <div class="login-container">
        <h2><i class="fas fa-user me-2"></i>Đăng Nhập Khách hàng</h2>
        <form action="${pageContext.request.contextPath}/login" method="POST">
            <div class="mb-3">
                <label for="loginValue" class="form-label">Số điện thoại</label>
                <input type="text" class="form-control" id="loginValue" name="loginValue" placeholder="Nhập số điện thoại" required>
            </div>
            <div class="mb-3">
                <label for="matKhau" class="form-label">Mật khẩu</label>
                <div class="input-group">
                    <input type="password" class="form-control" id="matKhau" name="matKhau" placeholder="Nhập mật khẩu" required>
                    <button type="button" class="btn btn-outline-secondary" id="toggleMatKhauBtn">
                        <i class="fas fa-eye" id="toggleMatKhauIcon"></i>
                    </button>
                </div>
            </div>
            <button type="submit" class="btn btn-success" id="loginButton">Đăng Nhập</button>
            <div id="error-message" class="alert alert-danger mt-3" role="alert" style="display: none;"></div>
        </form>
        <div class="text-center mt-3">
            <p>Chưa có tài khoản? <a href="../register/dang-ky.jsp" class="text-decoration-none">Đăng ký ngay</a></p>
            <a href="../index.jsp" class="text-decoration-none">Quay lại trang chủ</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.get('error') === 'true') {
            const errorMessage = urlParams.get('message') || 'Số điện thoại hoặc mật khẩu không đúng!';
            document.getElementById('error-message').textContent = errorMessage;
            document.getElementById('error-message').style.display = 'block';
        }

        // Toggle mật khẩu
        document.getElementById('toggleMatKhauBtn').addEventListener('click', function() {
            const passwordField = document.getElementById('matKhau');
            const icon = document.getElementById('toggleMatKhauIcon');
            if (passwordField.type === 'password') {
                passwordField.type = 'text';
                icon.classList.remove('fa-eye');
                icon.classList.add('fa-eye-slash');
            } else {
                passwordField.type = 'password';
                icon.classList.remove('fa-eye-slash');
                icon.classList.add('fa-eye');
            }
        });

        // Kiểm tra trước khi submit
        document.querySelector('form').addEventListener('submit', function(e) {
            const loginButton = document.getElementById('loginButton');
            const loginValue = document.getElementById('loginValue').value.trim();
            const phoneRegex = /^0\d{9,10}$/;
            const errorMessage = document.getElementById('error-message');

            if (!phoneRegex.test(loginValue)) {
                e.preventDefault();
                errorMessage.textContent = 'Số điện thoại không hợp lệ! Phải bắt đầu bằng 0 và có 10-11 chữ số!';
                errorMessage.style.display = 'block';
                return;
            }

            loginButton.disabled = true;
            loginButton.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Đang xử lý...';
            document.getElementById('matKhau').value = document.getElementById('matKhau').value.trim();
        });
    </script>
</body>
</html>