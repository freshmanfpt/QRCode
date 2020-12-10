package com.example.app.DAO;

public class maCode {
    private String maCode;
    private String theLoai;
    private String ngayThang;
    private String danhsach;

    public maCode(String maCode, String theLoai, String ngayThang, String danhsach) {
        this.maCode = maCode;
        this.theLoai = theLoai;
        this.ngayThang = ngayThang;
        this.danhsach = danhsach;
    }

    public String getMaCode() {
        return maCode;
    }

    public void setMaCode(String maCode) {
        this.maCode = maCode;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }

    public String getDanhsach() {
        return danhsach;
    }

    public void setDanhsach(String danhsach) {
        this.danhsach = danhsach;
    }
}
