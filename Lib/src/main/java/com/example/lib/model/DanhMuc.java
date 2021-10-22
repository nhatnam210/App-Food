package com.example.lib.model;

public class DanhMuc {
    public int Id;
    public String Tendanhmuc;
    public String Hinhanhdanhmuc;

    public DanhMuc() {
    }

    public DanhMuc(int id, String tendanhmuc, String hinhanhdanhmuc) {
        Id = id;
        Tendanhmuc = tendanhmuc;
        Hinhanhdanhmuc = hinhanhdanhmuc;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTendanhmuc() {
        return Tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        Tendanhmuc = tendanhmuc;
    }

    public String getHinhanhdanhmuc() {
        return Hinhanhdanhmuc;
    }

    public void setHinhanhdanhmuc(String hinhanhdanhmuc) {
        Hinhanhdanhmuc = hinhanhdanhmuc;
    }
}
