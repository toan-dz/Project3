package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VienPhiDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/k22cnt1_lexuantoan_project3?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; 
    private static final String PASSWORD = ""; 
    // Phương thức kết nối
    private Connection getConnection() throws SQLException {
        System.out.println("Attempting to connect to database...");
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Database connection successful");
        return conn;
    }

    // 📌 1️⃣ Kiểm tra đăng nhập người dùng
    public String kiemTraDangNhap(String taiKhoan, String matKhau) throws SQLException {
        String sql = "SELECT LoaiNguoiDung FROM NGUOIDUNG WHERE TaiKhoan = ? AND MatKhau = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            pstmt.setString(1, taiKhoan);
            pstmt.setString(2, matKhau);
            if (rs.next()) {
                return rs.getString("LoaiNguoiDung"); // Trả về loại người dùng
            }
        } catch (SQLException e) {
            System.out.println("SQLException in kiemTraDangNhap: " + e.getMessage());
            throw e; // Ném lại để xử lý ở cấp cao hơn
        }
        return null; // Trả về null nếu không tìm thấy
    }

    // 📌 2️⃣ Lấy thông tin bệnh nhân theo Mã Người Dùng
    public ResultSet layThongTinBenhNhan(int maNguoiDung) throws SQLException {
        String sql = "SELECT * FROM BENHNHAN WHERE MaNguoiDung = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maNguoiDung);
            rs = pstmt.executeQuery();
            // Trả về ResultSet, người gọi chịu trách nhiệm đóng
            return rs;
        } catch (SQLException e) {
            System.out.println("SQLException in layThongTinBenhNhan: " + e.getMessage());
            throw e;
        } finally {
            // Không đóng rs ở đây để người gọi có thể sử dụng
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    // 📌 3️⃣ Lấy thông tin bác sĩ theo Mã Người Dùng
    public ResultSet layThongTinBacSi(int maNguoiDung) throws SQLException {
        String sql = "SELECT * FROM BACSI WHERE MaNguoiDung = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maNguoiDung);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("SQLException in layThongTinBacSi: " + e.getMessage());
            throw e;
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    // 📌 4️⃣ Lấy danh sách hóa đơn theo Mã Bệnh Nhân
    public ResultSet layHoaDonBenhNhan(int maBenhNhan) throws SQLException {
        String sql = "SELECT * FROM HOADON WHERE MaBenhNhan = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maBenhNhan);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("SQLException in layHoaDonBenhNhan: " + e.getMessage());
            throw e;
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    // 📌 5️⃣ Lấy danh sách chi tiết hóa đơn theo Mã Hóa Đơn
    public ResultSet layChiTietHoaDon(int maHoaDon) throws SQLException {
        String sql = "SELECT * FROM CHITIETHOADON WHERE MaHoaDon = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maHoaDon);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("SQLException in layChiTietHoaDon: " + e.getMessage());
            throw e;
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    // 📌 6️⃣ Lấy danh sách dịch vụ
    public ResultSet layDanhSachDichVu() throws SQLException {
        String sql = "SELECT * FROM DICHVU";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("SQLException in layDanhSachDichVu: " + e.getMessage());
            throw e;
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }
}