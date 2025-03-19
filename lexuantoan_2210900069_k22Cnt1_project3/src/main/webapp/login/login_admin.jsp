<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập Admin/Bác sĩ</title>
    <link rel="stylesheet" href="../assets/css/login_admin.css">
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>
    <div class="login-container">
        <h2><i class="fas fa-user-md me-2"></i>Đăng Nhập Admin/Bác sĩ</h2>
        <!-- Hiển thị thông báo lỗi từ server -->
        <%
            String error = request.getParameter("error");
            if ("true".equals(error)) {
        %>
            <div class="alert alert-danger mt-3" role="alert">
                Tài khoản hoặc mật khẩu không đúng!
            </div>
        <% } %>
        <form action="${pageContext.request.contextPath}/dang-nhap-admin" method="POST">
            <div class="mb-3">
                <label for="taiKhoan" class="form-label">Tài khoản</label>
                <input type="text" class="form-control" id="taiKhoan" name="taiKhoan" placeholder="Nhập tài khoản" required>
            </div>
            <div class="mb-3">
                <label for="matKhau" class="form-label">Mật khẩu</label>
                <input type="password" class="form-control" id="matKhau" name="matKhau" placeholder="Nhập mật khẩu" required>
            </div>
            <button type="submit" class="btn btn-primary">Đăng Nhập</button>
        </form>
        <div class="text-center mt-3">
            <a href="../index.jsp" class="text-decoration-none">Quay lại trang chủ</a>
        </div>
    </div>

    <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>