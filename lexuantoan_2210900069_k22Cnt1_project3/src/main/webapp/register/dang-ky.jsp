<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký - Bệnh Nhân</title>
    <link rel="stylesheet" href="../assets/css/register.css">
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>
    <div class="login-container">
        <h2><i class="fas fa-user-plus me-2"></i>Đăng Ký Bệnh Nhân</h2>
        <!-- Hiển thị thông báo lỗi hoặc thành công -->
        <%
            String error = request.getParameter("error");
            String message = request.getParameter("message");
            if (error != null && error.equals("true")) {
        %>
            <div class="alert alert-danger mt-3" role="alert">
                <%= message != null ? java.net.URLDecoder.decode(message, "UTF-8") : "Có lỗi xảy ra! Vui lòng thử lại." %>
            </div>
        <%
            } else if (message != null && !error.equals("true")) {
        %>
            <div class="alert alert-success mt-3" role="alert">
                <%= java.net.URLDecoder.decode(message, "UTF-8") %>
            </div>
        <%
            }
        %>
        <form action="${pageContext.request.contextPath}/register" method="POST">
            <div class="mb-3">
                <label for="hoTen" class="form-label">Họ và Tên</label>
                <input type="text" class="form-control" id="hoTen" name="hoTen" placeholder="Nhập họ và tên" required>
            </div>
            <div class="mb-3">
                <label for="taiKhoan" class="form-label">Tài khoản</label>
                <input type="text" class="form-control" id="taiKhoan" name="taiKhoan" placeholder="Nhập tài khoản" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email" required>
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
            <div class="mb-3">
                <label for="confirmMatKhau" class="form-label">Xác nhận Mật khẩu</label>
                <div class="input-group">
                    <input type="password" class="form-control" id="confirmMatKhau" name="confirmMatKhau" placeholder="Xác nhận mật khẩu" required>
                    <button type="button" class="btn btn-outline-secondary" id="toggleConfirmMatKhauBtn">
                        <i class="fas fa-eye" id="toggleConfirmMatKhauIcon"></i>
                    </button>
                </div>
            </div>
            <div class="mb-3">
                <label for="soDienThoai" class="form-label">Số Điện Thoại</label>
                <input type="text" class="form-control" id="soDienThoai" name="soDienThoai" placeholder="Nhập số điện thoại" required>
            </div>
            <div class="mb-3">
                <label for="diaChi" class="form-label">Địa Chỉ</label>
                <input type="text" class="form-control" id="diaChi" name="diaChi" placeholder="Nhập địa chỉ">
            </div>
            <!-- Loại người dùng cố định là BenhNhan -->
            <input type="hidden" name="loaiNguoiDung" value="BenhNhan">
            <button type="submit" class="btn btn-primary">Đăng Ký</button>
            <div id="error-message" class="alert alert-danger mt-3" role="alert" style="display: none;">
                Mật khẩu không khớp hoặc thông tin không hợp lệ!
            </div>
        </form>
        <div class="text-center mt-3">
            <a href="../index.jsp" class="text-decoration-none">Quay lại trang chủ</a>
        </div>
        <div class="text-center mt-3">
            Đã có tài khoản? <a href="../login/login_khachhang.jsp" class="text-decoration-none">Đăng nhập</a>
        </div>
    </div>

    <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.get('error') === 'true') {
            document.getElementById('error-message').style.display = 'block';
        }

        // Toggle mật khẩu cho matKhau
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

        // Toggle mật khẩu cho confirmMatKhau
        document.getElementById('toggleConfirmMatKhauBtn').addEventListener('click', function() {
            const passwordField = document.getElementById('confirmMatKhau');
            const icon = document.getElementById('toggleConfirmMatKhauIcon');
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

        // Kiểm tra mật khẩu khớp trước khi submit
        document.querySelector('form').addEventListener('submit', function(event) {
            const matKhau = document.getElementById('matKhau').value;
            const confirmMatKhau = document.getElementById('confirmMatKhau').value;
            const soDienThoai = document.getElementById('soDienThoai').value;
            if (matKhau !== confirmMatKhau) {
                event.preventDefault();
                document.getElementById('error-message').style.display = 'block';
            } else if (!soDienThoai.match(/^0\d{9,10}$/)) {
                event.preventDefault();
                document.getElementById('error-message').textContent = 'Số điện thoại không hợp lệ! (Phải bắt đầu bằng 0 và 10-11 chữ số)';
                document.getElementById('error-message').style.display = 'block';
            }
        });
    </script>
</body>
</html>