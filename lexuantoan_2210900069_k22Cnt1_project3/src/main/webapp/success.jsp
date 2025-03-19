<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký thành công</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5 text-center">
        <h2 class="text-success">Đăng ký thành công!</h2>
        <p>Đang chuyển hướng đến trang đăng nhập...</p>
        <script>
            setTimeout(() => { window.location.href = '<%=request.getContextPath()%>/login/login_khachhang.jsp'; }, 2000);
        </script>
    </div>
</body>
</html>