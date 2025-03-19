<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.NguoiDungDAO, dao.HoaDonDAO, dao.HoaDonDAO.DichVu, java.util.List, java.text.DecimalFormat" %>
<% 
    String taiKhoan = (String) session.getAttribute("taiKhoan");
    if (taiKhoan == null || !"BenhNhan".equals(session.getAttribute("loaiNguoiDung"))) {
        response.sendRedirect(request.getContextPath() + "/login/login_khachhang.jsp?error=true");
        return;
    }

    NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
    HoaDonDAO hoaDonDAO = new HoaDonDAO();
    int maBenhNhan = nguoiDungDAO.getMaBenhNhanByTaiKhoan(taiKhoan);
    double tongTienChuaThanhToan = 0;
    List<DichVu> danhSachDichVu = null;
    Integer maHoaDon = (Integer) request.getAttribute("maHoaDon");

    if (maBenhNhan != -1) {
        tongTienChuaThanhToan = hoaDonDAO.getTongTienChuaThanhToan(maBenhNhan);
        danhSachDichVu = hoaDonDAO.getChiTietDichVuByMaBenhNhan(maBenhNhan);
    } else {
        request.setAttribute("errorMessage", "Không tìm thấy thông tin bệnh nhân!");
    }
    DecimalFormat df = new DecimalFormat("#,###");
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh Toán Hóa Đơn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>
    <header class="dashboard-header">
        <div class="container header-container">
            <div class="header-left">
                <img src="../assets/img/logo-stander.png" alt="Bệnh viện K" class="logo">
            </div>
            <div class="header-right">
                <div class="user-section">
                    <span class="welcome-text">Chào mừng, <%= taiKhoan %>!</span>
                    <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger btn-sm">Đăng Xuất</a>
                </div>
            </div>
        </div>
    </header>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mx-auto">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/benhnhan/dashboard.jsp"><i class="fas fa-home"></i> Trang Chủ</a></li>
                    <li class="nav-item"><a class="nav-link" href="#"><i class="fas fa-calendar-check"></i> Lịch Hẹn</a></li>
                    <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/thanh-toan"><i class="fas fa-money-bill-wave"></i> Thanh Toán</a></li>
                    <li class="nav-item"><a class="nav-link" href="#"><i class="fas fa-file-medical"></i> Hồ Sơ Y Tế</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <section class="container my-5">
        <h2 class="text-center mb-4">Chi Tiết Thanh Toán</h2>

        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } else if (request.getAttribute("successMessage") != null) { %>
            <div class="alert alert-success" role="alert">
                <%= request.getAttribute("successMessage") %>
            </div>
        <% } else if (danhSachDichVu != null && !danhSachDichVu.isEmpty()) { %>
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Danh Sách Dịch Vụ</h5>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Tên Dịch Vụ</th>
                                <th>Giá Tiền (VNĐ)</th>
                                <th>Số Lượng</th>
                                <th>Thành Tiền (VNĐ)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (DichVu dv : danhSachDichVu) { %>
                                <tr>
                                    <td><%= dv.getTenDichVu() %></td>
                                    <td><%= df.format(dv.getGiaTien()) %></td>
                                    <td><%= dv.getSoLuong() %></td>
                                    <td><%= df.format(dv.getThanhTien()) %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                    <p><strong>Tổng Tiền Chưa Thanh Toán:</strong> <%= df.format(tongTienChuaThanhToan) %> VNĐ</p>
                    <form action="${pageContext.request.contextPath}/thanh-toan" method="post">
                        <input type="hidden" name="action" value="confirm">
                        <button type="submit" class="btn btn-primary">Thanh Toán Ngay</button>
                    </form>
                </div>
            </div>
        <% } else { %>
            <div class="card">
                <div class="card-body">
                    <p class="text-success">Bạn không có hóa đơn nào cần thanh toán.</p>
                </div>
            </div>
        <% } %>
    </section>

    <footer class="bg-dark text-white text-center py-4 mt-5">
        <p>© 2025 Bệnh viện K. All rights reserved.</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>