package model;

public class BaoHiem {
    private int maBaoHiem;
    private int maBenhNhan;
    private String soThe;
    private String loaiBaoHiem;
    private String hanSuDung;
    private double tyLeHoTro;

    public BaoHiem() {}

    public BaoHiem(int maBaoHiem, int maBenhNhan, String soThe, String loaiBaoHiem, String hanSuDung, double tyLeHoTro) {
        this.maBaoHiem = maBaoHiem;
        this.maBenhNhan = maBenhNhan;
        this.soThe = soThe;
        this.loaiBaoHiem = loaiBaoHiem;
        this.hanSuDung = hanSuDung;
        this.tyLeHoTro = tyLeHoTro;
    }

    public int getMaBaoHiem() { return maBaoHiem; }
    public void setMaBaoHiem(int maBaoHiem) { this.maBaoHiem = maBaoHiem; }

    public int getMaBenhNhan() { return maBenhNhan; }
    public void setMaBenhNhan(int maBenhNhan) { this.maBenhNhan = maBenhNhan; }

    public String getSoThe() { return soThe; }
    public void setSoThe(String soThe) { this.soThe = soThe; }

    public String getLoaiBaoHiem() { return loaiBaoHiem; }
    public void setLoaiBaoHiem(String loaiBaoHiem) { this.loaiBaoHiem = loaiBaoHiem; }

    public String getHanSuDung() { return hanSuDung; }
    public void setHanSuDung(String hanSuDung) { this.hanSuDung = hanSuDung; }

    public double getTyLeHoTro() { return tyLeHoTro; }
    public void setTyLeHoTro(double tyLeHoTro) { this.tyLeHoTro = tyLeHoTro; }
}
