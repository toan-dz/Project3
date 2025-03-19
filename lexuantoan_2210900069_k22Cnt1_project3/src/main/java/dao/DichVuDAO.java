package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DichVu;

public class DichVuDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/K22CNT1_lexuantoan_Project3?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root"; // Thay bằng username của bạn
    private static final String DB_PASSWORD = ""; // Thay bằng password của bạn

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public List<DichVu> getAllDichVu() {
        List<DichVu> dichVuList = new ArrayList<>();
        String sql = "SELECT * FROM DICHVU";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DichVu dichVu = new DichVu();
                dichVu.setMaDichVu(rs.getInt("MaDichVu"));
                dichVu.setTenDichVu(rs.getString("TenDichVu"));
                dichVu.setMoTa(rs.getString("MoTa"));
                dichVu.setGiaTien(rs.getDouble("GiaTien"));
                dichVuList.add(dichVu);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dichVuList;
    }

    public boolean addDichVu(DichVu dichVu) {
        String sql = "INSERT INTO DICHVU (TenDichVu, MoTa, GiaTien) VALUES (?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dichVu.getTenDichVu());
            stmt.setString(2, dichVu.getMoTa());
            stmt.setDouble(3, dichVu.getGiaTien());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDichVu(DichVu dichVu) {
        String sql = "UPDATE DICHVU SET TenDichVu = ?, MoTa = ?, GiaTien = ? WHERE MaDichVu = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dichVu.getTenDichVu());
            stmt.setString(2, dichVu.getMoTa());
            stmt.setDouble(3, dichVu.getGiaTien());
            stmt.setInt(4, dichVu.getMaDichVu());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDichVu(int maDichVu) {
        String sql = "DELETE FROM DICHVU WHERE MaDichVu = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maDichVu);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public DichVu getDichVuById(int maDichVu) {
        String sql = "SELECT * FROM DICHVU WHERE MaDichVu = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maDichVu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new DichVu(
                    rs.getInt("MaDichVu"),
                    rs.getString("TenDichVu"),
                    rs.getString("MoTa"),
                    rs.getDouble("GiaTien")
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}