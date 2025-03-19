<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.DichVuDAO, model.DichVu, dao.NguoiDungDAO, java.util.List, model.NguoiDung" %>
<% 
    if (session.getAttribute("taiKhoan") == null || !"Admin".equals(session.getAttribute("loaiNguoiDung"))) {
        response.sendRedirect(request.getContextPath() + "/login/login_admin.jsp?error=true");
        return;
    }
    String taiKhoan = (String) session.getAttribute("taiKhoan");
    DichVuDAO dichVuDAO = new DichVuDAO();
    List<DichVu> dichVuList = dichVuDAO.getAllDichVu();
    
    NguoiDung nguoiDung = (NguoiDung) request.getAttribute("nguoiDung");
    Integer maBenhNhan = (Integer) request.getAttribute("maBenhNhan");
    String errorMessage = (String) request.getAttribute("errorMessage");
    String successMessage = (String) request.getAttribute("successMessage");
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Admin - Quản Lý Dịch Vụ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="../assets/css/admin_dashboard.css">
</head>
<body>
    <header class="dashboard-header">
        <div class="container header-container">
            <div class="header-left">
                <img src="../assets/img/logo-stander.png" alt="Bệnh viện K" class="logo">
            </div>
            <div class="header-right">
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
                    <li class="nav-item"><a class="nav-link active" href="#"><i class="fas fa-home"></i> Trang Chủ</a></li>
                    <li class="nav-item"><a class="nav-link" href="#"><i class="fas fa-user-md"></i> Quản Lý Bác Sĩ</a></li>
                    <li class="nav-item"><a class="nav-link" href="#"><i class="fas fa-users"></i> Quản Lý Bệnh Nhân</a></li>
                    <li class="nav-item"><a class="nav-link" href="#"><i class="fas fa-file-invoice"></i> Quản Lý Hóa Đơn</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <section class="container my-5">
        <h2 class="text-center mb-4">Quản Lý Dịch Vụ</h2>

        <div class="text-end mb-3">
            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addDichVuModal">
                <i class="fas fa-plus"></i> Thêm Dịch Vụ
            </button>
        </div>

        <table class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Mã Dịch Vụ</th>
                    <th>Tên Dịch Vụ</th>
                    <th>Mô Tả</th>
                    <th>Giá Tiền</th>
                    <th>Hành Động</th>
                </tr>
            </thead>
            <tbody>
                <% for (DichVu dichVu : dichVuList) { %>
                    <tr>
                        <td><%= dichVu.getMaDichVu() %></td>
                        <td><%= dichVu.getTenDichVu() %></td>
                        <td><%= dichVu.getMoTa() != null ? dichVu.getMoTa() : "N/A" %></td>
                        <td><%= String.format("%,.0f", dichVu.getGiaTien()) %> VNĐ</td>
                        <td>
                            <button class="btn btn-warning btn-sm edit-btn" 
                                    data-id="<%= dichVu.getMaDichVu() %>" 
                                    data-ten="<%= dichVu.getTenDichVu() %>" 
                                    data-mota="<%= dichVu.getMoTa() != null ? dichVu.getMoTa() : "" %>" 
                                    data-gia="<%= dichVu.getGiaTien() %>"
                                    data-bs-toggle="modal" data-bs-target="#editDichVuModal">
                                <i class="fas fa-edit"></i> Sửa
                            </button>
                            <a href="${pageContext.request.contextPath}/dichvu?action=delete&id=<%= dichVu.getMaDichVu() %>" 
                               class="btn btn-danger btn-sm" 
                               onclick="return confirm('Bạn có chắc muốn xóa dịch vụ này?');">
                                <i class="fas fa-trash"></i> Xóa
                            </a>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <h3 class="mt-5">Tạo Hóa Đơn Cho Bệnh Nhân</h3>

        <form action="${pageContext.request.contextPath}/search-patient" method="POST">
            <div class="mb-3">
                <label for="soDienThoai" class="form-label">Số Điện Thoại Bệnh Nhân</label>
                <input type="text" class="form-control" id="soDienThoai" name="soDienThoai" required>
            </div>
            <button type="submit" class="btn btn-info">Tìm Bệnh Nhân</button>
        </form>

        <% if (nguoiDung != null && maBenhNhan != null) { %>
            <div class="mt-4 mb-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Thông Tin Bệnh Nhân</h5>
                        <p><strong>Họ Tên:</strong> <%= nguoiDung.getHoTen() != null ? nguoiDung.getHoTen() : "N/A" %></p>
                        <p><strong>Tài Khoản:</strong> <%= nguoiDung.getTaiKhoan() != null ? nguoiDung.getTaiKhoan() : "N/A" %></p>
                        <p><strong>Số Điện Thoại:</strong> <%= nguoiDung.getSoDienThoai() != null ? nguoiDung.getSoDienThoai() : "N/A" %></p>
                        <p><strong>Email:</strong> <%= nguoiDung.getEmail() != null ? nguoiDung.getEmail() : "N/A" %></p>
                        <p><strong>Địa Chỉ:</strong> <%= nguoiDung.getDiaChi() != null ? nguoiDung.getDiaChi() : "N/A" %></p>
                    </div>
                </div>
            </div>

            <!-- Form Cập Nhật Thông Tin Bệnh Nhân -->
            <h5 class="mt-4">Cập Nhật Thông Tin Bệnh Nhân</h5>
            <form action="${pageContext.request.contextPath}/update-benhnhan-info" method="POST">
                <input type="hidden" name="maBenhNhan" value="<%= maBenhNhan %>">
                <input type="hidden" name="soDienThoai" value="<%= nguoiDung.getSoDienThoai() %>">
                <div class="mb-3">
                    <label for="ngaySinh" class="form-label">Ngày Sinh</label>
                    <input type="date" class="form-control" id="ngaySinh" name="ngaySinh" required>
                </div>
                <div class="mb-3">
                    <label for="gioiTinh" class="form-label">Giới Tính</label>
                    <select class="form-control" id="gioiTinh" name="gioiTinh" required>
                        <option value="Nam">Nam</option>
                        <option value="Nữ">Nữ</option>
                        <option value="Khác">Khác</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="soCMND" class="form-label">Số CMND</label>
                    <input type="text" class="form-control" id="soCMND" name="soCMND" required>
                </div>
                <div class="mb-3">
                    <label for="baoHiemYTe" class="form-label">Bảo Hiểm Y Tế</label>
                    <input type="text" class="form-control" id="baoHiemYTe" name="baoHiemYTe">
                </div>
                <div class="mb-3">
                    <label for="ghiChu" class="form-label">Ghi Chú</label>
                    <textarea class="form-control" id="ghiChu" name="ghiChu" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Cập Nhật Thông Tin</button>
            </form>

            <!-- Form Chọn Dịch Vụ và Tạo Hóa Đơn -->
            <h5 class="mt-4">Thêm Dịch Vụ và Thanh Toán</h5>
            <form action="${pageContext.request.contextPath}/hoadon" method="POST">
                <div class="mb-3">
                    <label for="dichVuIds" class="form-label">Chọn Dịch Vụ</label>
                    <select class="form-control" id="dichVuIds" name="dichVuIds" multiple required>
                        <% for (DichVu dichVu : dichVuList) { %>
                            <option value="<%= dichVu.getMaDichVu() %>"><%= dichVu.getTenDichVu() %> - <%= String.format("%,.0f", dichVu.getGiaTien()) %> VNĐ</option>
                        <% } %>
                    </select>
                </div>
                <input type="hidden" name="maBenhNhan" value="<%= maBenhNhan %>">
                <button type="submit" class="btn btn-success">Tạo Hóa Đơn</button>
            </form>
        <% } else if (errorMessage != null) { %>
            <div class="alert alert-danger mt-3" role="alert">
                <%= errorMessage %>
            </div>
        <% } else if (successMessage != null) { %>
            <div class="alert alert-success mt-3" role="alert">
                <%= successMessage %>
            </div>
        <% } %>
    </section>

    <!-- Modal Thêm Dịch Vụ -->
    <div class="modal fade" id="addDichVuModal" tabindex="-1" aria-labelledby="addDichVuModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/dichvu" method="POST">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addDichVuModalLabel">Thêm Dịch Vụ</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="action" value="add">
                        <div class="mb-3">
                            <label for="tenDichVu" class="form-label">Tên Dịch Vụ</label>
                            <input type="text" class="form-control" id="tenDichVu" name="tenDichVu" required>
                        </div>
                        <div class="mb-3">
                            <label for="moTa" class="form-label">Mô Tả</label>
                            <textarea class="form-control" id="moTa" name="moTa" rows="3"></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="giaTien" class="form-label">Giá Tiền (VNĐ)</label>
                            <input type="number" class="form-control" id="giaTien" name="giaTien" step="1000" min="0" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        <button type="submit" class="btn btn-primary">Thêm</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal Sửa Dịch Vụ -->
    <div class="modal fade" id="editDichVuModal" tabindex="-1" aria-labelledby="editDichVuModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/dichvu" method="POST">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editDichVuModalLabel">Sửa Dịch Vụ</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" id="editMaDichVu" name="maDichVu">
                        <div class="mb-3">
                            <label for="editTenDichVu" class="form-label">Tên Dịch Vụ</label>
                            <input type="text" class="form-control" id="editTenDichVu" name="tenDichVu" required>
                        </div>
                        <div class="mb-3">
                            <label for="editMoTa" class="form-label">Mô Tả</label>
                            <textarea class="form-control" id="editMoTa" name="moTa" rows="3"></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="editGiaTien" class="form-label">Giá Tiền (VNĐ)</label>
                            <input type="number" class="form-control" id="editGiaTien" name="giaTien" step="1000" min="0" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        <button type="submit" class="btn btn-primary">Cập Nhật</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

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
    <script>
        $(document).ready(function() {
            $('.edit-btn').on('click', function() {
                const id = $(this).data('id');
                const ten = $(this).data('ten');
                const mota = $(this).data('mota');
                const gia = $(this).data('gia');

                $('#editMaDichVu').val(id);
                $('#editTenDichVu').val(ten);
                $('#editMoTa').val(mota);
                $('#editGiaTien').val(gia);
            });
        });
    </script>
</body>
</html>