package com.example.lib;

public class NavForm {
    int hinhminhhoa;
    String noidung;

    public NavForm() {
    }

    public int getHinhminhhoa() {
        return hinhminhhoa;
    }

    public void setHinhminhhoa(int hinhminhhoa) {
        this.hinhminhhoa = hinhminhhoa;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public NavForm(int hinhminhhoa, String noidung) {
        this.hinhminhhoa = hinhminhhoa;
        this.noidung = noidung;
    }
}
