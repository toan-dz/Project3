package model;

public class NguoiDung {
    private int maNguoiDung;
    private String hoTen;
    private String taiKhoan;
    private String matKhau;
    private String loaiNguoiDung;
    private String soDienThoai;
    private String email;
    private String diaChi;

    public NguoiDung() {}

    public NguoiDung(int maNguoiDung, String hoTen, String taiKhoan, String matKhau, 
                     String loaiNguoiDung, String soDienThoai, String email, String diaChi) {
        this.maNguoiDung = maNguoiDung;
        this.hoTen = hoTen;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.loaiNguoiDung = loaiNguoiDung;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
    }

    public int getMaNguoiDung() { return maNguoiDung; }
    public void setMaNguoiDung(int maNguoiDung) { this.maNguoiDung = maNguoiDung; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getTaiKhoan() { return taiKhoan; }
    public void setTaiKhoan(String taiKhoan) { this.taiKhoan = taiKhoan; }

    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }

    public String getLoaiNguoiDung() { return loaiNguoiDung; }
    public void setLoaiNguoiDung(String loaiNguoiDung) { this.loaiNguoiDung = loaiNguoiDung; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
}