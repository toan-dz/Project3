package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.BenhNhan;

public class BenhNhanDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/K22CNT1_lexuantoan_Project3";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("Database connection established.");
        return conn;
    }

    public BenhNhan getBenhNhanByMaBenhNhan(int maBenhNhan) {
        String sql = "SELECT * FROM BENHNHAN WHERE MaBenhNhan = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maBenhNhan);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BenhNhan benhNhan = new BenhNhan();
                    benhNhan.setMaBenhNhan(rs.getInt("MaBenhNhan"));
                    benhNhan.setMaNguoiDung(rs.getInt("MaNguoiDung"));
                    benhNhan.setNgaySinh(rs.getDate("NgaySinh"));
                    benhNhan.setGioiTinh(rs.getString("GioiTinh"));
                    benhNhan.setSoCMND(rs.getString("SoCMND"));
                    benhNhan.setBaoHiemYTe(rs.getString("BaoHiemYTe"));
                    benhNhan.setGhiChu(rs.getString("GhiChu"));
                    System.out.println("Found BenhNhan - MaBenhNhan: " + maBenhNhan);
                    return benhNhan;
                } else {
                    System.out.println("No BenhNhan found for MaBenhNhan: " + maBenhNhan);
                    return null;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in getBenhNhanByMaBenhNhan: " + e.getMessage());
            throw new RuntimeException("Database error in getBenhNhanByMaBenhNhan: " + e.getMessage());
        }
    }

    public boolean updateBenhNhan(BenhNhan benhNhan) {
        String sql = "UPDATE BENHNHAN SET NgaySinh = ?, GioiTinh = ?, SoCMND = ?, BaoHiemYTe = ?, GhiChu = ? WHERE MaBenhNhan = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, benhNhan.getNgaySinh() != null ? new java.sql.Date(benhNhan.getNgaySinh().getTime()) : null);
            stmt.setString(2, benhNhan.getGioiTinh());
            stmt.setString(3, benhNhan.getSoCMND());
            stmt.setString(4, benhNhan.getBaoHiemYTe());
            stmt.setString(5, benhNhan.getGhiChu());
            stmt.setInt(6, benhNhan.getMaBenhNhan());

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Updated BenhNhan - MaBenhNhan: " + benhNhan.getMaBenhNhan() + ", Rows affected: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in updateBenhNhan: " + e.getMessage());
            return false;
        }
    }
}