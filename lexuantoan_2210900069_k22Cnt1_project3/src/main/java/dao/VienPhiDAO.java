package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VienPhiDAO {
    private String url = "jdbc:mysql://localhost:3306/k22cnt1_lexuantoan_project3"; 
    private String username = "root"; 
    private String password = ""; 

    public Connection connect() throws SQLException {
        try {
            // Đăng ký Driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Không tìm thấy Driver MySQL", e);
        }
        return DriverManager.getConnection(url, username, password);
    }

    // 📌 1️⃣ Kiểm tra đăng nhập người dùng (dựa trên TaiKhoan & MatKhau)
    public String kiemTraDangNhap(String taiKhoan, String matKhau) {
        String sql = "SELECT LoaiNguoiDung FROM NGUOIDUNG WHERE TaiKhoan = ? AND MatKhau = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, taiKhoan);
            pstmt.setString(2, matKhau);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("LoaiNguoiDung"); // Trả về loại người dùng
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }

    // 📌 2️⃣ Lấy thông tin bệnh nhân theo Mã Người Dùng
    public ResultSet layThongTinBenhNhan(int maNguoiDung) {
        String sql = "SELECT * FROM BENHNHAN WHERE MaNguoiDung = ?";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maNguoiDung);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 📌 3️⃣ Lấy thông tin bác sĩ theo Mã Người Dùng
    public ResultSet layThongTinBacSi(int maNguoiDung) {
        String sql = "SELECT * FROM BACSI WHERE MaNguoiDung = ?";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maNguoiDung);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 📌 4️⃣ Lấy danh sách hóa đơn theo Mã Bệnh Nhân
    public ResultSet layHoaDonBenhNhan(int maBenhNhan) {
        String sql = "SELECT * FROM HOADON WHERE MaBenhNhan = ?";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maBenhNhan);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 📌 5️⃣ Lấy danh sách chi tiết hóa đơn theo Mã Hóa Đơn
    public ResultSet layChiTietHoaDon(int maHoaDon) {
        String sql = "SELECT * FROM CHITIETHOADON WHERE MaHoaDon = ?";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maHoaDon);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 📌 6️⃣ Lấy danh sách dịch vụ
    public ResultSet layDanhSachDichVu() {
        String sql = "SELECT * FROM DICHVU";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
