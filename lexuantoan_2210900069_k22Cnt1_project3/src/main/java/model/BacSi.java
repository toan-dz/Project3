package model;

public class BacSi {
    private int maBacSi;
    private int maNguoiDung;
    private String chuyenKhoa;
    private int kinhNghiem;
    private String trinhDo;
    private String ghiChu;

    public BacSi() {}

    public BacSi(int maBacSi, int maNguoiDung, String chuyenKhoa, int kinhNghiem, String trinhDo, String ghiChu) {
        this.maBacSi = maBacSi;
        this.maNguoiDung = maNguoiDung;
        this.chuyenKhoa = chuyenKhoa;
        this.kinhNghiem = kinhNghiem;
        this.trinhDo = trinhDo;
        this.ghiChu = ghiChu;
    }

    public int getMaBacSi() { return maBacSi; }
    public void setMaBacSi(int maBacSi) { this.maBacSi = maBacSi; }

    public int getMaNguoiDung() { return maNguoiDung; }
    public void setMaNguoiDung(int maNguoiDung) { this.maNguoiDung = maNguoiDung; }

    public String getChuyenKhoa() { return chuyenKhoa; }
    public void setChuyenKhoa(String chuyenKhoa) { this.chuyenKhoa = chuyenKhoa; }

    public int getKinhNghiem() { return kinhNghiem; }
    public void setKinhNghiem(int kinhNghiem) { this.kinhNghiem = kinhNghiem; }

    public String getTrinhDo() { return trinhDo; }
    public void setTrinhDo(String trinhDo) { this.trinhDo = trinhDo; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}
