package com.example.lib.model;

import java.io.Serializable;

public class GioHang implements Serializable {
    int mamon;
    String tenmon;
    String hinhmon;
    String mota;
    long gia;
    int soluong;

    public GioHang() {
    }

    public GioHang(int mamon, String tenmon, String hinhmon, String mota, long gia, int soluong) {
        this.mamon = mamon;
        this.tenmon = tenmon;
        this.hinhmon = hinhmon;
        this.mota = mota;
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

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
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
