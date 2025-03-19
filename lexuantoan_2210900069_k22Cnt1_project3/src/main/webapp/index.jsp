<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ - Bệnh Viện</title>
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">
</head>
<body>
    <!-- Thanh header -->
    <header class="top-header">
        <div class="container header-container">
            <div class="header-left">
                <img src="assets/img/logo-stander.png" alt="Bệnh viện K" class="logo">
            </div>
            <div class="header-right">
                <span><i class="fas fa-phone"></i> Hotline: 0123 456 789</span>
                <span><i class="fas fa-headset"></i> Hỗ trợ: 1234-4321</span>
                <span class="search-box">
                    <input type="text" placeholder="Tìm kiếm...">
                    <button><i class="fas fa-search"></i></button>                    			                    
                </span>
                <!-- Nút đăng nhập với menu thả xuống -->
                <div class="auth-container">
                    <div class="auth-buttons">
                        <a href="#" class="btn-login" id="btn-login"><i class="fas fa-sign-in-alt"></i> Đăng Nhập</a>
                        <div class="dropdown-login" id="dropdown-login">
                            <a href="login/login_khachhang.jsp">Đăng nhập Khách hàng</a>
                            <a href="login/login_admin.jsp">Đăng nhập Admin</a>
                        </div>
                    </div>
                    <a href="register/dang-ky.jsp" class="btn-register"><i class="fas fa-user-plus"></i> Đăng Ký</a>
                </div>
            </div>
        </div>
    </header>

    <!-- Navbar và các phần khác giữ nguyên -->
    <nav class="navbar">
        <div class="container">
            <ul class="nav-links">
                <li><a href="index.jsp"><i class="fas fa-home"></i> Trang Chủ</a></li>
                <li><a href="#"><i class="fas fa-info-circle"></i> Giới Thiệu</a></li>
                <li><a href="#"><i class="fas fa-newspaper"></i> Tin Tức</a></li>
                <li><a href="#"><i class="fas fa-user-md"></i> Chuyên Khoa</a></li>
                <li><a href="#"><i class="fas fa-calendar-check"></i> Lịch Khám Bệnh</a></li>
                <li><a href="#"><i class="fas fa-money-bill-wave"></i> Giá Dịch Vụ</a></li>
            </ul>
        </div>
    </nav>

    <!-- Banner (Slideshow ảnh tự động) -->
    <section id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel" data-bs-interval="2500">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="assets/img/goi-dich-vu-y-te-co-ban.jpg" class="d-block w-100" alt="Dịch vụ y tế cơ bản">
            </div>
            <div class="carousel-item">
                <img src="assets/img/dich-vu-dac-biet.jpg" class="d-block w-100" alt="Hệ thống bệnh viện chất lượng cao">
            </div>
            <div class="carousel-item">
                <img src="assets/img/13052021-thong-bao-kham-bhyt-1.jpg.jpg" class="d-block w-100" alt="Đội ngũ y bác sĩ chuyên môn cao">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </section>

    <section class="container mt-5">
        <h2 class="text-center fw-bold mb-4">Dịch vụ nổi bật</h2>
        <div class="owl-carousel owl-theme">
            <div class="item">
                <img src="assets/img/home13_2_1.jpg" alt="Giới thiệu 1" class="img-fluid rounded">
                <p class="text-center mt-3 description">Khám sức khỏe doanh nghiệp</p>
            </div>
            <div class="item">
                <img src="assets/img/home13_2_2.jpg" alt="Giới thiệu 2" class="img-fluid rounded">
                <p class="text-center mt-3 description">Gói khám đa khoa</p>
            </div>
            <div class="item">
                <img src="assets/img/home13_2_3.jpg" alt="Giới thiệu 3" class="img-fluid rounded">
                <p class="text-center mt-3 description">Dịch vụ khám</p>
            </div>
            <div class="item">
                <img src="assets/img/home13_2_4.jpg" alt="Giới thiệu 4" class="img-fluid rounded">
                <p class="text-center mt-3 description">Thai sản chọn đời</p>
            </div>
        </div>
    </section>

    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
    <script>
        $(document).ready(function(){
            $(".owl-carousel").owlCarousel({
                loop: true,
                margin: 10,
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

    <script>
        const btnLogin = document.getElementById('btn-login');
        const dropdownLogin = document.getElementById('dropdown-login');

        btnLogin.addEventListener('click', function(e) {
            e.preventDefault();
            dropdownLogin.style.display = (dropdownLogin.style.display === 'block') ? 'none' : 'block';
        });

        document.addEventListener('click', function(e) {
            if (!btnLogin.contains(e.target) && !dropdownLogin.contains(e.target)) {
                dropdownLogin.style.display = 'none';
            }
        });
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>