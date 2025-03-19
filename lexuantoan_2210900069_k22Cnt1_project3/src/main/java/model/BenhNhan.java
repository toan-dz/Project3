package model;

import java.util.Date;

public class BenhNhan {
    private int maBenhNhan;
    private int maNguoiDung;
    private Date ngaySinh;
    private String gioiTinh;
    private String soCMND;
    private String baoHiemYTe;
    private String ghiChu;

    public BenhNhan() {}

    public BenhNhan(int maBenhNhan, int maNguoiDung, Date ngaySinh, String gioiTinh, String soCMND, String baoHiemYTe, String ghiChu) {
        this.maBenhNhan = maBenhNhan;
        this.maNguoiDung = maNguoiDung;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soCMND = soCMND;
        this.baoHiemYTe = baoHiemYTe;
        this.ghiChu = ghiChu;
    }

    public int getMaBenhNhan() { return maBenhNhan; }
    public void setMaBenhNhan(int maBenhNhan) { this.maBenhNhan = maBenhNhan; }

    public int getMaNguoiDung() { return maNguoiDung; }
    public void setMaNguoiDung(int maNguoiDung) { this.maNguoiDung = maNguoiDung; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getSoCMND() { return soCMND; }
    public void setSoCMND(String soCMND) { this.soCMND = soCMND; }

    public String getBaoHiemYTe() { return baoHiemYTe; }
    public void setBaoHiemYTe(String baoHiemYTe) { this.baoHiemYTe = baoHiemYTe; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}