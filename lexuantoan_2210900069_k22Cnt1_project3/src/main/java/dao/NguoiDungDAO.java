package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.NguoiDung;

public class NguoiDungDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/K22CNT1_lexuantoan_Project3";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("Database connection established.");
        return conn;
    }

    public boolean addNguoiDung(NguoiDung nguoiDung) {
        String sqlNguoiDung = "INSERT INTO NGUOIDUNG (HoTen, TaiKhoan, MatKhau, LoaiNguoiDung, SoDienThoai, Email, DiaChi) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlBenhNhan = "INSERT INTO BENHNHAN (MaNguoiDung) VALUES (?)";
        
        Connection conn = null;
        PreparedStatement stmtNguoiDung = null;
        PreparedStatement stmtBenhNhan = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            stmtNguoiDung = conn.prepareStatement(sqlNguoiDung, Statement.RETURN_GENERATED_KEYS);
            stmtNguoiDung.setString(1, nguoiDung.getHoTen().trim());
            stmtNguoiDung.setString(2, nguoiDung.getTaiKhoan().trim());
            stmtNguoiDung.setString(3, nguoiDung.getMatKhau());
            stmtNguoiDung.setString(4, nguoiDung.getLoaiNguoiDung().trim());
            stmtNguoiDung.setString(5, nguoiDung.getSoDienThoai().trim());
            stmtNguoiDung.setString(6, nguoiDung.getEmail().trim());
            stmtNguoiDung.setString(7, nguoiDung.getDiaChi().trim());

            int rowsAffected = stmtNguoiDung.executeUpdate();
            if (rowsAffected <= 0) {
                conn.rollback();
                return false;
            }

            ResultSet generatedKeys = stmtNguoiDung.getGeneratedKeys();
            int maNguoiDung = -1;
            if (generatedKeys.next()) {
                maNguoiDung = generatedKeys.getInt(1);
                System.out.println("Generated MaNguoiDung: " + maNguoiDung);
            } else {
                conn.rollback();
                return false;
            }

            if ("BenhNhan".equalsIgnoreCase(nguoiDung.getLoaiNguoiDung().trim())) {
                stmtBenhNhan = conn.prepareStatement(sqlBenhNhan);
                stmtBenhNhan.setInt(1, maNguoiDung);
                int rowsAffectedBenhNhan = stmtBenhNhan.executeUpdate();
                if (rowsAffectedBenhNhan <= 0) {
                    conn.rollback();
                    return false;
                }
                System.out.println("Added BenhNhan with MaNguoiDung: " + maNguoiDung);
            }

            conn.commit();
            System.out.println("Added user - TaiKhoan: " + nguoiDung.getTaiKhoan() + 
                             ", MatKhau: " + nguoiDung.getMatKhau() + 
                             ", Rows affected: " + rowsAffected);
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in addNguoiDung: " + e.getMessage());
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (stmtNguoiDung != null) stmtNguoiDung.close();
                if (stmtBenhNhan != null) stmtBenhNhan.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public NguoiDung getNguoiDungBySoDienThoai(String soDienThoai) {
        String sql = "SELECT * FROM NGUOIDUNG WHERE TRIM(SoDienThoai) = TRIM(?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, soDienThoai);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.setMaNguoiDung(rs.getInt("MaNguoiDung"));
                    nguoiDung.setHoTen(rs.getString("HoTen"));
                    nguoiDung.setTaiKhoan(rs.getString("TaiKhoan"));
                    nguoiDung.setMatKhau(rs.getString("MatKhau"));
                    nguoiDung.setLoaiNguoiDung(rs.getString("LoaiNguoiDung"));
                    nguoiDung.setSoDienThoai(rs.getString("SoDienThoai"));
                    nguoiDung.setEmail(rs.getString("Email"));
                    nguoiDung.setDiaChi(rs.getString("DiaChi"));
                    System.out.println("Found user - SoDienThoai: " + soDienThoai + ", TaiKhoan: " + nguoiDung.getTaiKhoan());
                    return nguoiDung;
                } else {
                    System.out.println("No user found for SoDienThoai: " + soDienThoai);
                    return null;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in getNguoiDungBySoDienThoai: " + e.getMessage());
            throw new RuntimeException("Database error in getNguoiDungBySoDienThoai: " + e.getMessage());
        }
    }

    public NguoiDung getNguoiDungByTaiKhoan(String taiKhoan) {
        String sql = "SELECT * FROM NGUOIDUNG WHERE TRIM(TaiKhoan) = TRIM(?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, taiKhoan);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.setMaNguoiDung(rs.getInt("MaNguoiDung"));
                    nguoiDung.setHoTen(rs.getString("HoTen"));
                    nguoiDung.setTaiKhoan(rs.getString("TaiKhoan"));
                    nguoiDung.setMatKhau(rs.getString("MatKhau"));
                    nguoiDung.setLoaiNguoiDung(rs.getString("LoaiNguoiDung"));
                    nguoiDung.setSoDienThoai(rs.getString("SoDienThoai"));
                    nguoiDung.setEmail(rs.getString("Email"));
                    nguoiDung.setDiaChi(rs.getString("DiaChi"));
                    System.out.println("Found user - TaiKhoan: " + taiKhoan);
                    return nguoiDung;
                } else {
                    System.out.println("No user found for TaiKhoan: " + taiKhoan);
                    return null;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in getNguoiDungByTaiKhoan: " + e.getMessage());
            throw new RuntimeException("Database error in getNguoiDungByTaiKhoan: " + e.getMessage());
        }
    }

    public int getMaBenhNhanBySoDienThoai(String soDienThoai) {
        String sql = "SELECT b.MaBenhNhan FROM NGUOIDUNG n LEFT JOIN BENHNHAN b ON b.MaNguoiDung = n.MaNguoiDung WHERE TRIM(n.SoDienThoai) = TRIM(?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, soDienThoai);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int maBenhNhan = rs.getInt("MaBenhNhan");
                    System.out.println("getMaBenhNhanBySoDienThoai: MaBenhNhan = " + maBenhNhan);
                    return maBenhNhan > 0 ? maBenhNhan : -1;
                } else {
                    System.out.println("getMaBenhNhanBySoDienThoai: No record found for SoDienThoai = " + soDienThoai);
                    return -1;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in getMaBenhNhanBySoDienThoai: " + e.getMessage());
            throw new RuntimeException("Database error in getMaBenhNhanBySoDienThoai: " + e.getMessage());
        }
    }

    public int getMaBenhNhanByTaiKhoan(String taiKhoan) {
        String sql = "SELECT b.MaBenhNhan FROM BENHNHAN b JOIN NGUOIDUNG n ON b.MaNguoiDung = n.MaNguoiDung WHERE TRIM(n.TaiKhoan) = TRIM(?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, taiKhoan);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("MaBenhNhan");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in getMaBenhNhanByTaiKhoan: " + e.getMessage());
            throw new RuntimeException("Database error in getMaBenhNhanByTaiKhoan: " + e.getMessage());
        }
        return -1;
    }

    // Thêm phương thức mới để cập nhật thông tin bệnh nhân
    public boolean updateBenhNhanInfo(int maBenhNhan, String ngaySinh, String gioiTinh, String soCMND, String baoHiemYTe, String ghiChu) {
        String sql = "UPDATE BENHNHAN SET NgaySinh = ?, GioiTinh = ?, SoCMND = ?, BaoHiemYTe = ?, GhiChu = ? WHERE MaBenhNhan = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ngaySinh);
            stmt.setString(2, gioiTinh);
            stmt.setString(3, soCMND);
            stmt.setString(4, baoHiemYTe);
            stmt.setString(5, ghiChu);
            stmt.setInt(6, maBenhNhan);

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Updated BenhNhan - MaBenhNhan: " + maBenhNhan + ", Rows affected: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in updateBenhNhanInfo: " + e.getMessage());
            return false;
        }
    }
}