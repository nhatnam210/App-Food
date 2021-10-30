package com.example.lib.model;

public class GioHang {
    int mamon;
    String tenmon;
    String hinhmon;
    long gia;
    int soluong;

    public GioHang() {
    }

    public GioHang(int mamon, String tenmon, String hinhmon, long gia, int soluong) {
        this.mamon = mamon;
        this.tenmon = tenmon;
        this.hinhmon = hinhmon;
        this.gia = gia;
        this.soluong = soluong;
    }

    public int getMamon() {
        return mamon;
    }

    public void setMamon(int mamon) {
        this.mamon = mamon;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public String getHinhmon() {
        return hinhmon;
    }

    public void setHinhmon(String hinhmon) {
        this.hinhmon = hinhmon;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
