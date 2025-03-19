package model;

import java.sql.Timestamp;
import java.util.List;

public class HoaDon {
    private int maHoaDon;
    private int maBenhNhan;
    private Timestamp ngayLap;
    private double tongTien;
    private String trangThai;
    private List<ChiTietHoaDon> chiTietHoaDon;

    // Getters và Setters
    public int getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(int maHoaDon) { this.maHoaDon = maHoaDon; }
    public int getMaBenhNhan() { return maBenhNhan; }
    public void setMaBenhNhan(int maBenhNhan) { this.maBenhNhan = maBenhNhan; }
    public Timestamp getNgayLap() { return ngayLap; }
    public void setNgayLap(Timestamp ngayLap) { this.ngayLap = ngayLap; }
    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public List<ChiTietHoaDon> getChiTietHoaDon() { return chiTietHoaDon; }
    public void setChiTietHoaDon(List<ChiTietHoaDon> chiTietHoaDon) { this.chiTietHoaDon = chiTietHoaDon; }
}