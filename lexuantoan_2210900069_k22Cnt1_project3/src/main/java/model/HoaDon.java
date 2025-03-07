package model;

public class HoaDon {
    private int maHoaDon;
    private int maBenhNhan;
    private String ngayLap;
    private double tongTien;
    private String trangThai;

    public HoaDon() {}

    public HoaDon(int maHoaDon, int maBenhNhan, String ngayLap, double tongTien, String trangThai) {
        this.maHoaDon = maHoaDon;
        this.maBenhNhan = maBenhNhan;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public int getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(int maHoaDon) { this.maHoaDon = maHoaDon; }

    public int getMaBenhNhan() { return maBenhNhan; }
    public void setMaBenhNhan(int maBenhNhan) { this.maBenhNhan = maBenhNhan; }

    public String getNgayLap() { return ngayLap; }
    public void setNgayLap(String ngayLap) { this.ngayLap = ngayLap; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
