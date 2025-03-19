package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HoaDonDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/K22CNT1_lexuantoan_Project3";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("Database connection established.");
        return conn;
    }

    public int getUnpaidHoaDonId(int maBenhNhan) {
        String sql = "SELECT MaHoaDon FROM HOADON WHERE MaBenhNhan = ? AND TrangThai = 'Chưa Thanh Toán'";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maBenhNhan);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("MaHoaDon");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in getUnpaidHoaDonId: " + e.getMessage());
        }
        return -1;
    }

    public int createHoaDon(int maBenhNhan) {
        String sql = "INSERT INTO HOADON (MaBenhNhan, TrangThai) VALUES (?, 'Chưa Thanh Toán')";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, maBenhNhan);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in createHoaDon: " + e.getMessage());
        }
        return -1;
    }

    public boolean addChiTietHoaDon(int maHoaDon, int maDichVu, int soLuong, double thanhTien) {
        String sql = "INSERT INTO CHITIETHOADON (MaHoaDon, MaDichVu, SoLuong, ThanhTien) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maHoaDon);
            stmt.setInt(2, maDichVu);
            stmt.setInt(3, soLuong);
            stmt.setDouble(4, thanhTien);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in addChiTietHoaDon: " + e.getMessage());
            return false;
        }
    }

    public boolean updateTongTien(int maHoaDon) {
        String sql = "UPDATE HOADON SET TongTien = (SELECT SUM(ThanhTien) FROM CHITIETHOADON WHERE MaHoaDon = ?) WHERE MaHoaDon = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maHoaDon);
            stmt.setInt(2, maHoaDon);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in updateTongTien: " + e.getMessage());
            return false;
        }
    }

    public double getGiaTienDichVu(int maDichVu) {
        String sql = "SELECT GiaTien FROM DICHVU WHERE MaDichVu = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maDichVu);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("GiaTien");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in getGiaTienDichVu: " + e.getMessage());
        }
        return 0;
    }

    public boolean updateTrangThaiThanhToan(int maHoaDon) {
        String sql = "UPDATE HOADON SET TrangThai = 'Đã Thanh Toán' WHERE MaHoaDon = ? AND TrangThai = 'Chưa Thanh Toán'";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maHoaDon);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Updated TrangThai for MaHoaDon: " + maHoaDon + ", Rows affected: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in updateTrangThaiThanhToan: " + e.getMessage());
            return false;
        }
    }

    public double getTongTien(int maHoaDon) {
        String sql = "SELECT TongTien FROM HOADON WHERE MaHoaDon = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maHoaDon);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("TongTien");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in getTongTien: " + e.getMessage());
        }
        return 0;
    }

    // Thêm phương thức mới để tính tổng tiền chưa thanh toán của bệnh nhân
    public double getTongTienChuaThanhToan(int maBenhNhan) {
        String sql = "SELECT SUM(TongTien) AS TongTienChuaThanhToan FROM HOADON WHERE MaBenhNhan = ? AND TrangThai = 'Chưa Thanh Toán'";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maBenhNhan);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("TongTienChuaThanhToan");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in getTongTienChuaThanhToan: " + e.getMessage());
        }
        return 0;
    }
}