<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.HoaDonDAO, dao.NguoiDungDAO, java.text.DecimalFormat" %>
<% 
    System.out.println("Loading benhnhan/dashboard.jsp for user: " + session.getAttribute("taiKhoan"));
    if (session.getAttribute("taiKhoan") == null) {
        System.out.println("No session found, redirecting to login_khachhang.jsp");
        response.sendRedirect(request.getContextPath() + "/login/login_khachhang.jsp?error=true");
        return;
    }
    String taiKhoan = (String) session.getAttribute("taiKhoan");
    String loaiNguoiDung = (String) session.getAttribute("loaiNguoiDung");

    NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
    HoaDonDAO hoaDonDAO = new HoaDonDAO();
    int maBenhNhan = nguoiDungDAO.getMaBenhNhanByTaiKhoan(taiKhoan);
    double tongTienChuaThanhToan = 0;
    try {
        if (maBenhNhan != -1) {
            tongTienChuaThanhToan = hoaDonDAO.getTongTienChuaThanhToan(maBenhNhan);
            System.out.println("TongTienChuaThanhToan for MaBenhNhan " + maBenhNhan + ": " + tongTienChuaThanhToan);
        } else {
            System.out.println("No BenhNhan found for TaiKhoan: " + taiKhoan);
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error fetching payment info: " + e.getMessage());
    }
    DecimalFormat df = new DecimalFormat("#,###");
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Bệnh Nhân - <%= taiKhoan %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">
    <link rel="stylesheet" href="../assets/css/dashboard.css">
</head>
<body>
    <header class="dashboard-header">
        <div class="container header-container">
            <div class="header-left">
                <img src="../assets/img/logo-stander.png" alt="Bệnh viện K" class="logo">
            </div>
            <div class="header-right">
                <span><i class="fas fa-phone"></i> Hotline: 0123 456 789</span>
                <span><i class="fas fa-headset"></i> Hỗ trợ: 1234-4321</span>
                <span class="search-box">
                    <input type="text" placeholder="Tìm kiếm...">
                    <button><i class="fas fa-search"></i></button>
                </span>
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
                    <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/benhnhan/dashboard.jsp"><i class="fas fa-home"></i> Trang Chủ</a></li>
                    <li class="nav-item"><a class="nav-link" href="#"><i class="fas fa-calendar-check"></i> Lịch Hẹn</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/thanh-toan"><i class="fas fa-money-bill-wave"></i> Thanh Toán</a></li>
                    <li class="nav-item"><a class="nav-link" href="#"><i class="fas fa-file-medical"></i> Hồ Sơ Y Tế</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <section class="container my-5">
        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
            <div class="col">
                <div class="card card-custom h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-user fa-3x text-primary mb-3"></i>
                        <h5 class="card-title">Thông Tin Cá Nhân</h5>
                        <p class="card-text">Xem và cập nhật thông tin cá nhân của bạn.</p>
                        <a href="#" class="btn btn-primary">Xem Chi Tiết</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card card-custom h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-calendar-check fa-3x text-success mb-3"></i>
                        <h5 class="card-title">Lịch Hẹn</h5>
                        <p class="card-text">Kiểm tra và đặt lịch khám bệnh.</p>
                        <a href="#" class="btn btn-success">Xem Lịch Hẹn</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card card-custom h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-money-bill-wave fa-3x text-warning mb-3"></i>
                        <h5 class="card-title">Thanh Toán Viện Phí</h5>
                        <p class="card-text">Tổng cần thanh toán: <%= df.format(tongTienChuaThanhToan) %> VNĐ</p>
                        <a href="${pageContext.request.contextPath}/benhnhan/thanh-toan.jsp" class="btn btn-warning text-white">Thanh Toán Ngay</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card card-custom h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-file-medical fa-3x text-danger mb-3"></i>
                        <h5 class="card-title">Hồ Sơ Y Tế</h5>
                        <p class="card-text">Xem kết quả xét nghiệm và hồ sơ y tế.</p>
                        <a href="#" class="btn btn-danger">Xem Hồ Sơ</a>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="container my-5 bg-light p-4 rounded">
        <h2 class="text-center mb-4">Dịch Vụ Nổi Bật</h2>
        <div class="owl-carousel owl-theme">
            <div class="item">
                <div class="card">
                    <img src="../assets/img/home13_2_1.jpg" alt="Khám sức khỏe doanh nghiệp" class="card-img-top img-fluid rounded">
                    <div class="card-body text-center">
                        <p class="card-text">Khám sức khỏe doanh nghiệp</p>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="card">
                    <img src="../assets/img/home13_2_2.jpg" alt="Gói khám đa khoa" class="card-img-top img-fluid rounded">
                    <div class="card-body text-center">
                        <p class="card-text">Gói khám đa khoa</p>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="card">
                    <img src="../assets/img/home13_2_3.jpg" alt="Dịch vụ khám" class="card-img-top img-fluid rounded">
                    <div class="card-body text-center">
                        <p class="card-text">Dịch vụ khám</p>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="card">
                    <img src="../assets/img/home13_2_4.jpg" alt="Thai sản chọn đời" class="card-img-top img-fluid rounded">
                    <div class="card-body text-center">
                        <p class="card-text">Thai sản chọn đời</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <footer class="bg-dark text-white text-center py-4 mt-5">
        <p>© 2025 Bệnh viện K. All rights reserved.</p>
        <div class="mb-3">
            <a href="#" class="text-white me-3"><i class="fab fa-facebook-f"></i></a>
            <a href="#" class="text-white me-3"><i class="fab fa-twitter"></i></a>
            <a href="#" class="text-white"><i class="fab fa-instagram"></i></a>
        </div>
    </footer>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
    <script>
        $(document).ready(function(){
            $(".owl-carousel").owlCarousel({
                loop: true,
                margin: 20,
                nav: true,
                dots: true,
                autoplay: true,
                autoplayTimeout: 3000,
                autoplayHoverPause: true,
                responsive: {
                    0: { items: 1 },
                    600: { items: 2 },
                    1000: { items: 3 }
                }
            });
        });
    </script>
</body>
</html>